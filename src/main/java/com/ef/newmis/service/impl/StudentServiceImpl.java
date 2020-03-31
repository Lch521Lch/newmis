package com.ef.newmis.service.impl;

import com.ef.newmis.entity.AbsentDetailEntity;
import com.ef.newmis.entity.AbsentEntity;
import com.ef.newmis.entity.PayEntity;
import com.ef.newmis.entity.StudentEntity;
import com.ef.newmis.mapper.StudentMapper;
import com.ef.newmis.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 学生相关方法
 *
 * @author dirxu
 * @date 2020-02-13
 */
@Slf4j
@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<StudentEntity> searchStudent(String searchVal) throws Exception {
        return studentMapper.searchStudent(searchVal);

    }


    @Override
    public void deleteStudent(String studentNo) throws Exception {
        studentMapper.delStudent(studentNo);
    }

    /**
     * 增加学生信息
     *
     * @param studentEntity 学生姓名
     * @return 1：成功，2：重复
     */
    @Override
    public int insertStudent(StudentEntity studentEntity) throws Exception {
        // 产看是否有重复学生记录
        List<StudentEntity> studentList = studentMapper.check(studentEntity);
        // 有重复学生记录
        if (studentList.size() > 0) {
            return 2;
        }
        studentMapper.insertStudent(studentEntity);
        return 1;


    }


    @Override
    public void updateStudent(StudentEntity studentEntity) throws Exception {
        studentMapper.updateStudent(studentEntity);
    }

    @Override
    public List<StudentEntity> check(StudentEntity studentEntity) throws Exception {
        return studentMapper.check(studentEntity);
    }


    @Override
    public List<StudentEntity> searchByScheduleId(String scheduleId) throws Exception {
        return studentMapper.searchByScheduleId(scheduleId);
    }

    @Override
    public List<AbsentEntity> checkAbsent(String scheduleId) throws Exception {
        return studentMapper.checkAbsent(scheduleId);
    }


    @Override
    public void createAbsent(List<AbsentEntity> absenetList) throws Exception {

        studentMapper.createAbsent(absenetList);
    }

    @Override
    public List<AbsentDetailEntity> searchAbsentByStudentName(String searchVal) throws Exception {
        return studentMapper.searchAbsentByStudentName(searchVal);
    }

    @Override
    public void pay(PayEntity payEntity) throws Exception {
        studentMapper.pay(payEntity);
    }


    @Override
    public List<PayEntity>  searchPayRecord(String searchVal) throws  Exception{
        return studentMapper.searchPayRecord(searchVal);

    }
    @Override
    public  List<PayEntity>  searchPayRecordWithDateRange(String  beginDate,String endDate) throws  Exception{
        return studentMapper.searchPayRecordWithDateRange(beginDate,endDate);
    }


    @Override
    public void   updatePay(PayEntity payEntity) throws  Exception{
        studentMapper.updatePay(payEntity);
    }



}




