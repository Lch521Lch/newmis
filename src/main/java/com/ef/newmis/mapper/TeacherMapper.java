package com.ef.newmis.mapper;

import com.ef.newmis.entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;


/**
 *  教师接口
 * @author dirxu

 * @date 2020-02-14
 */
public interface TeacherMapper {
    /**
     * 查找教师
     *
     * @param teacherName 教师姓名，可以是中文，英文，拼音，和联系电话
     * @return 查询教师结果集
     * @throws   Exception 数据库相关异常
     */
    List<TeacherEntity> searchTeacher(@Param("teacherName") String teacherName) throws Exception;

    /**
     * 删除教师信息
     *
     * @param teacherId 教师id
     * @throws   Exception 数据库相关异常
     */

    void delTeacher(@Param("teacherId") String teacherId) throws Exception;


    /**
     * 增加教师
     *
     * @param teacherEntity 教师实体
     * @throws   Exception 数据库相关异常
     */

    void insertTeacher(TeacherEntity teacherEntity) throws Exception;







}
