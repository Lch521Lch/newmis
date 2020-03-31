package com.ef.newmis.mapper;

import com.ef.newmis.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 课程接口
 * @author dirxu
 * @date 2020-02-14
 */

public interface CourseMapper {


    /**
     * 查找所有课程     *
     *
     * @return 查询课程结果集
     */
    List<CourseEntity> showAllCourse() throws Exception;



    /**
     * 修改课程
     * @throws  Exception 数据库相关异常
     * @param  courseEntity 课程实体
     *
     *
     */
    void updateCourse(CourseEntity courseEntity) throws Exception;






    /**
     * 查找所有课程
     * @param courseCode courseCode
     * @return 查询课程结果集
     */
    CourseEntity searchCourse(@Param("courseCode") String courseCode ) throws Exception;



}
