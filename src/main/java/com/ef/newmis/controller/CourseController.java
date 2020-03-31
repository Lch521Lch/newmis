package com.ef.newmis.controller;


import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.CourseEntity;
import com.ef.newmis.entity.StudentEntity;
import com.ef.newmis.service.impl.CourseServiceImpl;
import com.ef.newmis.service.impl.TeacherServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:课程信息Controller
 *
 * @author dirxu
 * @create 2020-02-14
 */
@Slf4j
@RequestMapping("/course")
@RestController

public class CourseController {

    @Autowired
    CourseServiceImpl courseServiceImpl;


    /**
     * 课程信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/search")
    public JSONObject search() {
        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        try {
            //查询课程信息
             List<CourseEntity> courseList = courseServiceImpl.showAllCourse();

            /**构建前台返回结果集*/
            if (courseList.size() == 0 || courseList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关课程信息。");
                resultJson.put("state", "warning");
            } else {
                resultJson.put("total", courseList.size());
                resultJson.put("list", courseList);
                resultJson.put("msg", "课程信息检索成功");
                resultJson.put("state", "success");
            }
        }
        catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }


    /**
     * 课程信息修改
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/update")
    public JSONObject update(@RequestBody String questJson) {

        log.debug("修改课程信息 Json \t" + questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        //请求关键参数，反序列化
        CourseEntity courseEntity = JSONObject.parseObject(questJson, CourseEntity.class);

        try {
            //修改学生信息
            courseServiceImpl.updateCourse(courseEntity);
            resultJson.put("msg", "修改课程信息成功");
            resultJson.put("state", "success");
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;
    }





}
