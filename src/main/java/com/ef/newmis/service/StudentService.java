package com.ef.newmis.service;

import com.ef.newmis.entity.AbsentDetailEntity;
import com.ef.newmis.entity.AbsentEntity;
import com.ef.newmis.entity.PayEntity;
import com.ef.newmis.entity.StudentEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 学生相关接口信息
 * @author dirxu
 * @date 2020-02-13
 */

public interface StudentService {
    /**
     * 查询
     * @param studentName 学生姓名
     * @return 学生信息结果集
     * @throws   Exception 数据库相关异常
     */
    List<StudentEntity> searchStudent(String studentName)  throws Exception;

    /**
     * 删除学生信息
     * @param studentNo 学生编号
     * @return 数据库操作是否成功
     * @throws   Exception 数据库相关异常
     */
     void deleteStudent(String studentNo)  throws Exception;

    /**
     * 增加学生信息
     * @param studentEntity 学生信息
     * @return 1：成功，2：重复，0：失败
     * @throws   Exception 数据库相关异常
     */

    int  insertStudent(StudentEntity studentEntity)  throws Exception;

    /**
     * 修改学生信息
     * @param studentEntity 学生信息
     * @throws   Exception 数据库相关异常
     */
    void updateStudent(StudentEntity studentEntity)  throws Exception;

    /**
     * 确认学生记录是否存在
     * @param studentEntity 学生信息
     * @throws   Exception 数据库相关异常
     * @return 学生记录集合
     */

    List<StudentEntity> check (StudentEntity studentEntity)  throws Exception;


    /**
     * 修改学生信息
     * @param scheduleId  课程表id
     * @throws  Exception 数据库相关异常
     * @return  学生记录集合
     */

    List<StudentEntity> searchByScheduleId(String scheduleId) throws  Exception;



    /**
     * 确认是否已经有出勤记录
     *
     * @param scheduleId  课程表id
     * @throws  Exception 数据库相关异常
     * @return  出勤记录 集合
     */

    List<AbsentEntity> checkAbsent(String scheduleId) throws  Exception;

    /**
     * 确认是否已经有出勤记录
     *
     * @param absenetList  前台传回来的 出勤结果集合
     * @throws  Exception 数据库相关异常
     */
    void  createAbsent(List<AbsentEntity> absenetList)  throws  Exception;


    /**
     * 生成学生出勤记录
     *
     * @param searchVal  姓名 or 联系电话

     * @throws  Exception 数据库相关异常
     * @return  符合条件的出勤记录集合
     */
    List<AbsentDetailEntity>  searchAbsentByStudentName(String searchVal) throws  Exception;

    /**
     * 生成缴费记录
     *
     * @param payEntity  缴费实体
     * @throws  Exception 数据库相关异常
     */

    void   pay (PayEntity payEntity) throws  Exception;


    /**
     * 查询缴费记录
     *
     * @param searchVal  查询条件
     * @throws  Exception 数据库相关异常
     * @return 缴费记录
     */
    List<PayEntity>  searchPayRecord(String searchVal) throws  Exception;


    /**
     * 查询缴费记录
     *
     * @param beginDate  查询条件
     * @param endDate
     * @throws  Exception 数据库相关异常
     * @return 缴费记录
     */
    List<PayEntity>  searchPayRecordWithDateRange(String  beginDate,String endDate) throws  Exception;




    /**
     * 修改缴费记录
     *
     * @param payEntity  查询条件
     * @throws  Exception 数据库相关异常

     */

    void   updatePay(PayEntity payEntity) throws  Exception;


}
