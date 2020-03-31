package com.ef.newmis.mapper;

import com.ef.newmis.entity.ClassEntity;
import com.ef.newmis.entity.ClassHisEntity;
import com.ef.newmis.entity.ScheduleEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 班级接口
 *
 * @author dirxu
 * @date 2020-02-15
 */

public interface ClassMapper {


    /**
     * 查找所有班级     *
     *
     * @param classEntity 班级查询实体
     * @return 查询课程结果集
     * @throws Exception 数据库相关异常
     */
    List<ClassEntity> searchClass(ClassEntity classEntity) throws Exception;

    /**
     * 根据班级编码查找班级
     *
     * @param classEntity 班级查询实体
     * @return 查询课程结果集
     * @throws Exception 数据库相关异常
     */
    ClassEntity
    searchClassByCode(String classCode) throws Exception;


    /**
     * 新增班级
     *
     * @param courseEntity 班级实体    *
     * @throws Exception 数据库相关异常
     */
    void insertClass(ClassEntity classEntity) throws Exception;


    /**
     * 编辑班级
     *
     * @param courseEntity 班级实体    *
     * @throws Exception 数据库相关异常
     */
    void updateClass(ClassEntity classEntity) throws Exception;


    /**
     * 编辑班级
     *
     * @param courseCode 班级编号
     * @throws Exception 数据库相关异常
     */
    void delClass(String courseCode) throws Exception;


    /**
     * 核对课程     *
     *
     * @param classEntity 班级查询实体
     * @return 查询课程结果集
     * @throws Exception 数据库相关异常
     */
    List<ClassEntity> checkClass(String classCode) throws Exception;


    /**
     * 查询选课历史信息     *
     *
     * @param classEntity 班级查询实体
     * @return 查询选课历史结果集
     * @throws Exception 数据库相关异常
     */
    List<ClassHisEntity> searchClassHis(ClassHisEntity classHisEntity) throws Exception;


    /**
     * 新增选课历史
     *
     * @param classHisEntity 选课实体    *
     * @throws Exception 数据库相关异常
     */
    void insertClassHis(ClassHisEntity classHisEntity) throws Exception;


    //delClassHis


    /**
     * 删除选课信息
     *
     * @param classHisEntity 选课历史实体
     * @throws Exception 数据库相关异常
     */
    void delClassHis(ClassHisEntity classHisEntity) throws Exception;
    /**
     * 生成班级课程表
     *
     * @param scheduleList
     * @throws Exception
     */

    void createSchedule(List<ScheduleEntity> scheduleList) throws Exception;


    /**
     * 查询班级课程表
     *
     * @param classCode
     * @return Schedule 结构集
     * @throws Exception
     */

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
     * @return  确定是否有选课记录
     */

    Integer checkChooseClass(String studentNo ) throws Exception;


    /**
     * 修改时结课时间
     *
     * @param classCode 班级代码    *
     * @param endDate 结课日期
     * @param endWeek 结课周
     *
     * @throws Exception 数据库相关异常
     */
    void updateClassEndDate(String classCode,String endDate ,Integer endWeek) throws Exception;


    /**
     * 查询已经结课的班级
     *
     * @return 已经结课的班级
     *
     * @throws Exception 数据库相关异常
     */

    List<ClassEntity>   autoUpdate() throws Exception;


    /**
     * 修改班级状态
     *
     * @param classCode
     *
     * @throws Exception 数据库相关异常
     */

    void  updateClassStatus (String  classCode) throws Exception;


    /**
     * 获取课程表的最后一天
     *
     * @param classCode
     * @throws Exception 数据库相关异常
     * @return  maxDate
     *
     * */


    String  getMaxDate(String classCode) throws Exception;




}
