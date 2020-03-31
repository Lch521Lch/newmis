package com.ef.newmis.mapper;

import com.ef.newmis.entity.AbsentDetailEntity;
import com.ef.newmis.entity.AbsentEntity;
import com.ef.newmis.entity.PayEntity;
import com.ef.newmis.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生接口
 * @author dirxu
 * @date 2020-02-12
 */
public interface StudentMapper {
    /**
     * 查找学生
     *
     * @param studentName 学生姓名，可以是中文，英文，拼音，和联系电话
     * @return 查询学生结果集
     */
    List<StudentEntity> searchStudent(@Param("searchVal") String searchVal)  throws Exception;

    /**
     * 删除学生信息
     *
     * @param studentId 学生id
     * @throws   Exception 数据库相关异常                  *
     */

    void delStudent(@Param("studentNo") String studentNo)  throws Exception;


    /**
     * 增加学生
     *
     * @param studentEntity 学生实体
     * @throws   Exception 数据库相关异常                     *
     */

    void insertStudent(StudentEntity studentEntity)  throws Exception;




    /**
     * 检查是否已有记录
     *
     * @param studentEntity 学生实体
     * @return 查询学生结果集
     * @throws   Exception 数据库相关异常
     */

    List<StudentEntity> check( StudentEntity studentEntity)  throws Exception;



    /**
     * 修改学生信息
     *
     * @param studentEntity 学生信息
     * @throws   Exception 数据库相关异常
     */
    void updateStudent(StudentEntity studentEntity)  throws Exception;


    /**
     * 修改学生信息
     *
     * @param scheduleId  课程表id
     * @throws  Exception 数据库相关异常
     */

    List<StudentEntity> searchByScheduleId(String scheduleId) throws  Exception;


    /**
     * 确认是否已经有出勤记录
     *
     * @param scheduleId  课程表id
     * @throws  Exception 数据库相关异常
     */

    List<AbsentEntity> checkAbsent(String scheduleId) throws  Exception;

    /**
     * 生成学生出勤记录
     *
     * @param absenetList  课程表id

     * @throws  Exception 数据库相关异常
     */

    void createAbsent( List<AbsentEntity> absenetList) throws  Exception;

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
     * 修改缴费记录
     *
     * @param payEntity  查询条件
     * @throws  Exception 数据库相关异常

     */

   void   updatePay(PayEntity payEntity) throws  Exception;



    /**
     * 查询缴费记录
     *
     * @param beginDate  查询条件
     * @param endDate
     * @throws  Exception 数据库相关异常
     * @return 缴费记录
     */
    List<PayEntity>  searchPayRecordWithDateRange(String  beginDate,String endDate) throws  Exception;








}


