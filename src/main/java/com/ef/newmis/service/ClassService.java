package com.ef.newmis.service;

import com.ef.newmis.entity.ClassEntity;
import com.ef.newmis.entity.ClassHisEntity;
import com.ef.newmis.entity.ScheduleEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 班级相关接口信息
 * @author dirxu
 * @date 2020-02-15
 */

public interface ClassService {
    /**
     * 查询
     * @param classEntity 班级姓名
     * @return 班级信息结果集
     * @throws   Exception 数据库相关异常
     */
    List<ClassEntity> searchClass(ClassEntity classEntity)  throws Exception;



    /**
     * 增加班级信息
     * @param classEntity 班级信息
     * @return 1：成功，2：重复，0：失败
     * @throws   Exception 数据库相关异常
     */

    int  insertClass(ClassEntity classEntity)  throws Exception;

    /**
     * 修改班级信息
     * @param classEntity 班级信息
     * @throws   Exception 数据库相关异常
     */
    void updateClass(ClassEntity classEntity)  throws Exception;

    /**
     * 删除班级信息
     * @param courseCode 班级代码
     * @return  数据库更新是否成功
     * @throws   Exception 数据库相关异常
     */

    void delClass(String  courseCode) throws Exception;


    /**
     * 查询选课历史信息     *
     * @param classHisEntity 班级查询实体
     * @return 查询选课历史结果集
     * @throws Exception 数据库相关异常
     */

    List<ClassHisEntity> searchClassHis(ClassHisEntity  classHisEntity) throws Exception;


    /**
     * 新增选课历史
     * @throws  Exception 数据库相关异常
     * @param  classHisEntity 选课实体    *
     *
     */
    void insertClassHis(ClassHisEntity classHisEntity) throws Exception;


    /**
     * 删除选课信息
     * @throws  Exception 数据库相关异常
     * @param  classHisEntity 选课历史实体
     *     */

    void delClassHis(ClassHisEntity classHisEntity) throws Exception;


    /**
     *
     * 生成班级课程表
     * @throws  Exception 数据库相关异常
     *
     * @param courseCode
     * */


    int  createSchedule(String  classCode ) throws Exception;


    /**
     * 查询班级课程表
     * @param classCode
     * @return  Schedule 结构集
     * */

    List<ScheduleEntity> searchSchedule(String classCode) throws Exception;


    /**
     * 查询班级课程表
     *
     * @param scheduleTeacher
     * @param scheduleDate
     * @param room
     * @return Schedule 结构集
     * @throws Exception
     */

    List<ScheduleEntity> searchSchedulePrint(String scheduleTeacher,String scheduleDate ,String room ) throws Exception;




    /**
     * 编辑课程表
     *
     * @param schedule
     * @throws Exception 对方法可能抛出的异常进行说明
     */
    void updateSchedule(ScheduleEntity schedule) throws Exception;


    /**
     * 查找当前是否记录
     *
     * @param studentNo
     * @throws Exception 对方法可能抛出的异常进行说明
     */
     Integer  checkChooseClass(String studentNo) throws Exception;


    /**
     * 自动更新班级信息，包括班级状态，和自动结束时间
     *
     * @param studentNo
     * @throws Exception 对方法可能抛出的异常进行说明
     */

     void autoUpdate() throws Exception;





}
