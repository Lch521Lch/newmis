package com.ef.newmis.controller;


import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.TeacherEntity;
import com.ef.newmis.service.impl.TeacherServiceImpl;
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
 * 描述:教师信息Controller
 *
 * @author dirxu
 * @create 2020-02-14
 */
@Slf4j
@RequestMapping("/teacher")
@RestController

public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;


    /**
     * @des 教师信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/search")
    public JSONObject search(@RequestBody String questJson) {


        // 初始化返回json
        JSONObject resultJson = new JSONObject();
        //解析 请求json
        //解析 请求json
        JsonExtraTools jsonExtraTools = new JsonExtraTools();
        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);

        //请求关键参数，务必确保前台请求的时候要有 teacherName关键字段
        String searchVal = questMap.get("searchVal");

        log.debug("查询教师信息：teacherName\t" + searchVal);
        try {
            //查询教师信息
            List<TeacherEntity> teacherList = teacherServiceImpl.searchTeacher(searchVal);


            /**构建前台返回结果集*/
            if (teacherList.size() == 0 || teacherList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关教师信息。");
                resultJson.put("state", "warning");
            } else {
                resultJson.put("total", teacherList.size());
                resultJson.put("list", teacherList);
                resultJson.put("msg", "教师信息检索成功");
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
     * 教师信息删除
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

        //请求关键参数teacherId，前台无比要做非空等逻辑判断
        String teacherId = questMap.get("teacherId");

        log.debug("删除教师ID：teacherId\t" + teacherId);
        try {
            //查询教师信息
            teacherServiceImpl.deleteTeacher(teacherId);
            resultJson.put("msg", "教师信息删除成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 增加教师记录-建立档案
     *
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody String questJson) {

        log.debug("新建教师信息 Json \t" + questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //请求关键参数
        TeacherEntity teacherEntity = JSONObject.parseObject(questJson, TeacherEntity.class);

        //生成教师ID -UUID
        teacherEntity.setTeacherId(IdUtil.simpleUUID());


        try {
            //增加教师信息
            teacherServiceImpl.insertTeacher(teacherEntity);
            resultJson.put("msg", "新增教师信息成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


}
