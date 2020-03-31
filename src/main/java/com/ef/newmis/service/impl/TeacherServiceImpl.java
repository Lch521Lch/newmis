package com.ef.newmis.service.impl;

import com.ef.newmis.entity.TeacherEntity;
import com.ef.newmis.mapper.TeacherMapper;
import com.ef.newmis.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 教师相关方法
 * @author dirxu

 * @date 2020-02-14
 */
 @Slf4j
 @Service("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<TeacherEntity> searchTeacher(String teacherName) throws Exception {
        List<TeacherEntity> teacherList = teacherMapper.searchTeacher(teacherName);
        return teacherList;
    }


    @Override
    public void deleteTeacher(String teacherId) throws Exception {

            teacherMapper.delTeacher(teacherId);


    }

    /**
     * 增加教师信息
     *
     * @param teacherName 教师姓名
     * @return 1：成功，2：重复，0：失败
     * @throws Exception 数据库异常
     */
    @Override
    public void  insertTeacher(TeacherEntity teacherEntity) throws Exception {

            teacherMapper.insertTeacher(teacherEntity);

    }




}
