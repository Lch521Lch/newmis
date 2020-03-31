package com.ef.newmis.service;

import com.ef.newmis.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
/**
 * 课程相关接口信息
 * @author dirxu
 * @date 2020-02-14
 */
public interface CourseService {

    /**
     * 获得所有课程信息
     *
     * @return 课程信息结果集
     * @throws   Exception 数据库相关异常
     */
    List<CourseEntity> showAllCourse() throws Exception;



    /**
     * 修改所有课程信息
     *
     * @throws   Exception 数据库相关异常
     */
    void updateCourse(CourseEntity courseEntity)  throws Exception;


    /**
     * 查找所有课程
     * @param courseCode courseCode
     * @return 查询课程结果集
     */
    CourseEntity searchCourse(@Param("courseCode") String courseCode ) throws Exception;

}
