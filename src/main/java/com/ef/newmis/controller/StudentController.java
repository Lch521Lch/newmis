package com.ef.newmis.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.PassToken;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.AbsentDetailEntity;
import com.ef.newmis.entity.AbsentEntity;
import com.ef.newmis.entity.PayEntity;
import com.ef.newmis.entity.StudentEntity;
import com.ef.newmis.service.impl.StudentServiceImpl;
import com.ef.newmis.util.JsonExtraTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 *
 * @author dirxu
 * @create 2020-01-15
 */
@Slf4j
@RequestMapping("/student")
@RestController

public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;


    /**
     * 学生信息查询
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/search")
    public JSONObject search(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();
        //解析 请求json

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //请求关键参数，务必确保前台请求的时候要有 studentName关键字段
        String searchVal = questMap.get("searchVal");

        log.debug("学生查询条件：searchVal\t" + searchVal);

        try {
            /**查询学生信息*/
            List<StudentEntity> studentList = studentServiceImpl.searchStudent(searchVal);

            /**构建前台返回结果集*/
            if (studentList.size() == 0 || studentList.isEmpty()) {
                resultJson.put("total", 0);
                resultJson.put("list", studentList);
                resultJson.put("msg", "没有查询到相关学生信息。");
                resultJson.put("state", "warning");
            } else {
                resultJson.put("total", studentList.size());
                resultJson.put("list", studentList);
                resultJson.put("msg", "学生检索成功");
                resultJson.put("state", "success");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * 学生信息删除
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/delete")
    public JSONObject delete(@RequestBody String questJson) {

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //请求关键参数studentId，前台无比要做非空等逻辑判断
        String studentNo = questMap.get("studentNo");

        log.debug("删除学生编号：studentNo\t" + studentNo);
        try {
            //删除学生信息
            studentServiceImpl.deleteStudent(studentNo);
            resultJson.put("msg", "学生信息删除成功");
            resultJson.put("state", "success");

        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * 增加学生记录-建立档案
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody String questJson) {

        log.debug("新建学生信息 Json \t" + questJson);

        //请求关键参数
        StudentEntity studentEntity;

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        studentEntity = JSONObject.parseObject(questJson, StudentEntity.class);
        try {
            //增加学生信息
            int insertResult = studentServiceImpl.insertStudent(studentEntity);

            if (insertResult == 1) {
                resultJson.put("msg", "新增学生信息成功");
                resultJson.put("state", "success");
            } else if (insertResult == 2) {
                resultJson.put("msg", "新增学记录失败，已有相同联系电话和相同拼音名字电话");
                resultJson.put("state", "warning");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 修改学生记录-建立档案
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/update")
    public JSONObject update(@RequestBody String questJson) {

        log.debug("学生信息 Json \t" + questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //请求关键参数，反序列化
        StudentEntity studentEntity = JSONObject.parseObject(questJson, StudentEntity.class);

        try {
            //修改学生信息
            List<StudentEntity> StudentList = studentServiceImpl.check(studentEntity);

            if (StudentList.size() > 0) {
                studentServiceImpl.updateStudent(studentEntity);
                resultJson.put("msg", "修改学生信息成功");
                resultJson.put("state", "success");
            } else {
                resultJson.put("msg", "没有查询到此学员信息");
                resultJson.put("state", "warning");
            }


        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 根据 ScheduleId 查询 学生信息
     *
     * @author dirxu
     * @date 2020-03-20
     */
    @UserLoginToken
    @RequestMapping("/searchByScheduleId")
    public JSONObject searchByScheduleId(@RequestBody String questJson) {
        log.debug("学生出勤请求参数:\t" + questJson);


        // 初始化返回json
        JSONObject resultJson = new JSONObject();
        //解析 请求json

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //请求关键参数，务必确保前台请求的时候要有 ScheduleId

        String scheduleId = questMap.get("scheduleId");


        try {
            List<AbsentEntity> absentList;
            absentList = studentServiceImpl.checkAbsent(scheduleId);
            //absentList size 大于 零，说明已经生成过记录
            if (absentList == null || absentList.size() == 0) {

                List<StudentEntity> studentList = studentServiceImpl.searchByScheduleId(scheduleId);

                if (studentList != null && studentList.size() > 0) {
                    resultJson.put("total", studentList.size());
                    resultJson.put("list", studentList);
                    resultJson.put("msg", "学生检索成功");
                    resultJson.put("state", "success");
                } else {
                    resultJson.put("msg", "没有查询到此学员信息");
                    resultJson.put("state", "warning");
                    resultJson.put("total", studentList.size());
                    resultJson.put("list", studentList);
                }

            } else {
                resultJson.put("msg", "此课程表已经生成出勤记录");
                resultJson.put("state", "error");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }


        return resultJson;

    }


    /**
     * 生成课程表的出勤记录
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/createAbsent")
    public JSONObject createAbsent(@RequestBody String questJson) {


        log.debug("学生出勤请求参数:\t" + questJson);

        List<AbsentEntity> absentEntityList = JSONObject.parseArray(questJson, AbsentEntity.class);

        JSONObject resultJson = new JSONObject();


        try {

            studentServiceImpl.createAbsent(absentEntityList);

            resultJson.put("msg", "出勤记录成功");
            resultJson.put("state", "success");


        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * 学生信息查询
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/searchAbsentByStudentName")
    public JSONObject searchAbsentByStudentName(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject result = new JSONObject();
        //解析 请求json

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //请求关键参数，务必确保前台请求的时候要有 studentName关键字段
        String search = questMap.get("search");

        log.debug("学生查询条件：searchVal\t" + search);

        try {
            /**查询学生出勤信息*/
            List<AbsentDetailEntity> AbsentDetailEntityList = studentServiceImpl.searchAbsentByStudentName(search);

            /**构建前台返回结果集*/


            if (AbsentDetailEntityList != null && AbsentDetailEntityList.size() > 0) {
                result.put("total", AbsentDetailEntityList.size());
                result.put("list", AbsentDetailEntityList);
                result.put("msg", "学生出勤记录检索成功");
                result.put("state", "success");
            } else {
                result.put("msg", "没有查询到此学员出勤记录");
                result.put("state", "warning");
                result.put("total", AbsentDetailEntityList.size());
                result.put("list", AbsentDetailEntityList);
            }


        } catch (Exception e) {
            log.error(e.toString());
            result.put("msg", "数据库连接失败");
            result.put("state", "error");
        }
        return result;
    }







    /**
     * 缴费
     *
     * @author dirxu
     */

    @UserLoginToken
    @RequestMapping("/pay")
    public JSONObject pay(@RequestBody String payJson) {

        log.debug("缴费 Json \t" + payJson);

        //请求关键参数
        PayEntity payEntity;

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        payEntity = JSONObject.parseObject(payJson, PayEntity.class);

        payEntity.setPayId(IdUtil.simpleUUID());
        payEntity.setPayDate(DateUtil.format(new Date(), "yyyyMMdd"));


        try {

            studentServiceImpl.pay(payEntity);

            resultJson.put("msg", "缴费记录成功");
            resultJson.put("state", "success");


        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 查询缴费记录
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/searchPayRecord")
    public JSONObject searchPayRecord(@RequestBody String payQuer) {

        JSONObject resultJson = new JSONObject();

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        try {

        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(payQuer);



        //请求关键参数，务必确保前台请求的时候要有 studentName关键字段
        String search = questMap.get("search");



           List<PayEntity> payList =  studentServiceImpl.searchPayRecord(search);

            if (payList != null && payList.size() > 0) {
                resultJson.put("total", payList.size());
                resultJson.put("list", payList);
                resultJson.put("msg", "缴费记录查询成功");
                resultJson.put("state", "success");
            } else {
                resultJson.put("msg", "没有查询缴费记录");
                resultJson.put("state", "warning");
                resultJson.put("total", payList.size());
                resultJson.put("list", payList);
            }

        }
        catch (NullPointerException e2){
            log.error("空指针异常");
            log.error(e2.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");

        }


        catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    @UserLoginToken
    @RequestMapping("/searchPayRecordWithDateRange")
    public JSONObject searchPayRecordWithDateRange(@RequestBody String WithDateRange) {

        JSONObject resultJson = new JSONObject();

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        try {

            HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(WithDateRange);

            //请求关键参数，务必确保前台请求的时候要有 studentName关键字段
            String beginDate = questMap.get("beginDate");
            String endDate = questMap.get("endDate");

            List<PayEntity> payList =  studentServiceImpl.searchPayRecordWithDateRange(beginDate,endDate);

            if (payList != null && payList.size() > 0) {
                resultJson.put("total", payList.size());
                resultJson.put("list", payList);
                resultJson.put("state", "success");
                resultJson.put("msg", "缴费记录查询成功");
            } else {
                resultJson.put("msg", "没有查询缴费记录");
                resultJson.put("state", "warning");
                resultJson.put("total", payList.size());
                resultJson.put("list", payList);
            }
        }
        catch (NullPointerException e2){
            log.error("空指针异常");
            log.error(e2.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");

        }
        catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 修改缴费记录
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/updatePay")
    public JSONObject updatePay(@RequestBody String updatePay) {
         log.debug(updatePay);

        JSONObject resultJson = new JSONObject();

        PayEntity payEntity = JSONObject.parseObject(updatePay, PayEntity.class);

        try {

            studentServiceImpl.updatePay(payEntity);
            resultJson.put("msg", "缴费修改成功");
            resultJson.put("state", "success");


        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }


        return resultJson ;
    }



}
