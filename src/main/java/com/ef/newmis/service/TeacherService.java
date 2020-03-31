package com.ef.newmis.service;

import com.ef.newmis.entity.TeacherEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 教师相关接口信息
 *
 * @author dirxu
 * @date 2020-02-13
 */
public interface TeacherService {

    /**
     * 查询
     *
     * @param teacherName 教师姓名
     * @return 学生信息结果集
     * @throws Exception 数据库相关异常
     */
    List<TeacherEntity> searchTeacher(String teacherName) throws Exception;

    /**
     * 删除教师信息
     *
     * @param teacherId 学生ID
     * @throws Exception 数据库相关异常
     */
    void deleteTeacher(String teacherId) throws Exception;

    /**
     * 增加教师信息
     *
     * @param teacherEntity 教师信息
     * @throws Exception 数据库相关异常
     */

    void insertTeacher(TeacherEntity teacherEntity) throws Exception;


}
