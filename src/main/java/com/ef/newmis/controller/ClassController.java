package com.ef.newmis.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.ClassEntity;
import com.ef.newmis.entity.ClassHisEntity;
import com.ef.newmis.entity.ScheduleEntity;
import com.ef.newmis.entity.StudentEntity;
import com.ef.newmis.service.impl.ClassServiceImpl;
import com.ef.newmis.util.JsonExtraTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


/**
 * 描述:
 *
 * @author dirxu
 * @create 2020-01-15
 */
@Slf4j
@RequestMapping("/class")
@RestController

public class ClassController {

    @Autowired
    ClassServiceImpl classServiceImpl;


    /**
     * @param
     * @des 班级信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/search")
    public JSONObject search(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        log.debug("查询班级信息：questJson\t" + questJson);


        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        // 获取前台参数，"query",是前台写死的参数
        String queryParam;
        queryParam = questMap.get("query");

        //ClassEntity classEntity = JSONObject.parseObject(questJson, ClassEntity.class);

        if ("".equals(queryParam)) {
            queryParam = "%";
        }

        ClassEntity classEntity = new ClassEntity();
        classEntity.setRoomCode(queryParam);
        classEntity.setCourseCode(queryParam);
        classEntity.setStatus(queryParam);
        classEntity.setTeacher(queryParam);

        try {
            /**查询班级信息*/
            List<ClassEntity> classList = classServiceImpl.searchClass(classEntity);

            log.debug("classList\t" + classList.size());


            /**构建前台返回结果集*/
            if (classList.size() == 0 || classList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关班级信息。");
                resultJson.put("list", classList);
                resultJson.put("state", "warning");
            } else {
                resultJson.put("total", classList.size());
                resultJson.put("list", classList);
                resultJson.put("msg", "班级信息检索成功");
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
     * 课程信息删除
     *
     * @author dirxu
     * @date 2020-2-15
     */
    @UserLoginToken
    @RequestMapping("/delete")
    public JSONObject delete(@RequestBody String questJson) {

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //classCode，前台无比要做非空等逻辑判断
        String classCode = questMap.get("classCode");

        log.debug("删除班级code：classCode\t" + classCode);
        try {
            //删除学生信息
            classServiceImpl.delClass(classCode);
            resultJson.put("msg", "班级信息删除成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * 增加班级记录-建立档案
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody String questJson) {

        log.debug("新建班级信息 Json \t" + questJson);

        //请求关键参数
        StudentEntity studentEntity;

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        ClassEntity classEntity = JSONObject.parseObject(questJson, ClassEntity.class);

        try {
            //增加学生信息
            int insertResult = classServiceImpl.insertClass(classEntity);

            if (insertResult == 1) {
                resultJson.put("msg", "新增班级信息成功");
                resultJson.put("state", "success");
            } else if (insertResult == 2) {
                resultJson.put("msg", "新增班级录失败，已有相同记录");
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
     * 修改班级记录-建立档案
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/update")
    public JSONObject update(@RequestBody String questJson) {

        log.debug("修改班级信息 Json \t" + questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //请求关键参数，反序列化
        ClassEntity classEntity = JSONObject.parseObject(questJson, ClassEntity.class);

        try {
            //修改学生信息
            classServiceImpl.updateClass(classEntity);
            resultJson.put("msg", "修改班级信息成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * @param
     * @des 班级信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/searchHistory")
    public JSONObject searchHistory(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        log.debug("查询历史选课信息：questJson\t" + questJson);

        ClassHisEntity classHisEntity = JSONObject.parseObject(questJson, ClassHisEntity.class);



        try {
            /**查询班级信息*/
            List<ClassHisEntity> classHisList = classServiceImpl.searchClassHis(classHisEntity);

            log.debug("classHisList\t" + classHisList.size());


            /**构建前台返回结果集*/
            if (classHisList.size() == 0 || classHisList.isEmpty()) {
                resultJson.put("msg", "没有查询到历史选课信息。");
                resultJson.put("list", classHisList);
                resultJson.put("state", "warning");
                resultJson.put("total", 0);
            } else {
                resultJson.put("total", classHisList.size());
                resultJson.put("list", classHisList);
                resultJson.put("msg", "班级信息检索成功");
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
     * 增加班级记录-新增选课
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/insertHis")
    public JSONObject insertHis(@RequestBody String questJson) {

        log.debug("新建班级信息 Json \t" + questJson);


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        ClassHisEntity classHisEntity = JSONObject.parseObject(questJson, ClassHisEntity.class);
        try {

        Integer  recordNumbers = classServiceImpl.checkChooseClass(classHisEntity.getStudentNo());


        if (recordNumbers>0){

            resultJson.put("msg", "该学生已经在一个Activ状态的班级中，选课前请删除上次选课记录，或者联系管理员修改上次班级状态");
            resultJson.put("state", "error");
        } else
        {

                //增加选课信息
                classServiceImpl.insertClassHis(classHisEntity);
                resultJson.put("msg", "选课成功");
                resultJson.put("state", "success");

            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败，或是已经在当前班级内");
            resultJson.put("state", "error");
        }



        return resultJson;

    }


    /**
     * 课程信息删除
     *
     * @author dirxu
     * @date 2020-2-15
     */
    @UserLoginToken
    @RequestMapping("/delClassHis")
    public JSONObject delClassHis(@RequestBody String questJson) {

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        ClassHisEntity classHisEntity = JSONObject.parseObject(questJson, ClassHisEntity.class);


        log.debug("删除选课历史code：classCode\t" + questJson);
        try {
            //删除选课历史
            classServiceImpl.delClassHis(classHisEntity);
            resultJson.put("msg", "班级信息删除成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * @param
     * @des 生产班级课程表
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/createSchedule")
    public JSONObject createSchedule(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        log.debug("查询班级课程表：questJson\t" + questJson);


        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        // 获取前台参数，"classCode",是前台写死的参数
        String classCode = questMap.get("classCode");


        try {

            int checkFlag = classServiceImpl.createSchedule(classCode);
            //  checkFlag == 0 ,说明已经有课程表记录
            if (checkFlag == 0) {
                resultJson.put("msg", "已经有此班级课程表信息");
                resultJson.put("state", "warning");
            } else {
                resultJson.put("msg", "班级课程表信息创建成功");
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
     * 查询班级课程表
     */
    @UserLoginToken
    @RequestMapping("/searchSchedule")
    public JSONObject searchSchedule(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        log.debug("查询班级课程信息：questJson\t" + questJson);


        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        // 获取前台参数，"classCode",是前台写死的参数
        String classCode = questMap.get("classCode");
        if (classCode.length() < 1) {
            classCode = "%";
        }

        try {
            /**查询班级课程表信息*/
            List<ScheduleEntity> scheduleList = classServiceImpl.searchSchedule(classCode);

            log.debug("scheduleList\t" + scheduleList.size());


            /**构建前台返回结果集*/
            if (scheduleList.size() == 0 || scheduleList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关班级信息。");
                resultJson.put("state", "warning");
                resultJson.put("list", scheduleList);
            } else {
                resultJson.put("total", scheduleList.size());
                resultJson.put("list", scheduleList);
                resultJson.put("msg", "班级信息检索成功");
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
     * 查询班级课程表
     */
    @UserLoginToken
    @RequestMapping("/searchSchedulePrint")
    public JSONObject searchSchedulePrint(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        log.debug("查询班级课程信息：questJson\t" + questJson);


        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        // 获取前台参数，"classCode",是前台写死的参数
        String scheduleDate = questMap.get("scheduleDate");

        String scheduleTeacher = questMap.get("scheduleTeacher");

        String room = questMap.get("room");


        try {
            /**查询班级课程表信息*/
            List<ScheduleEntity> scheduleList = classServiceImpl.searchSchedulePrint(scheduleTeacher,scheduleDate,room);

            log.debug("scheduleList\t" + scheduleList.size());
            /**构建前台返回结果集*/
            if (scheduleList.size() == 0 || scheduleList.isEmpty()) {
                resultJson.put("list", scheduleList);
                resultJson.put("state", "warning");
                resultJson.put("msg", "没有查询到相关班级信息。");
            } else {
                resultJson.put("total", scheduleList.size());
                resultJson.put("list", scheduleList);
                resultJson.put("msg", "班级信息检索成功");
                resultJson.put("state", "success");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }




    //updateSchedule

    /**
     * 编辑班级课程表
     */
    @UserLoginToken
    @RequestMapping("/updateSchedule")
    public JSONObject v(@RequestBody String questJson) {

        log.debug("修改班级信息 Json \t" + questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //请求关键参数，反序列化
        ScheduleEntity schedule = JSONObject.parseObject(questJson, ScheduleEntity.class);

        try {
            //修改学生信息
            classServiceImpl.updateSchedule(schedule);
            resultJson.put("msg", "修改班级课程表信息成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


}
