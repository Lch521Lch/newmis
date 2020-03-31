package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 班级实体
 * @author dirxu
 * @date 2020-02-11
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassEntity {

    String id;
    /**班级代码*/
    String classCode;
    /**课程代码*/
    String courseCode;
    /**教室代码*/
    String roomCode;
    /**教师1*/
    String teacher1;
    /**上课星期1*/
    String week1;
    /**上课时间1*/
    String time1;
    /**下课时间1*/
    String endTime1;




    /**教师2*/
    String teacher2;
    /**上课星期2*/
    String week2;
    /**上课时间2*/
    String time2;
    /**下课时间2*/
    String endTime2;

    /**班主任*/
    String headTeacher;


    /**课程开始时间*/
    String beginDate;

    /**开始周*/
    Integer beginWeek;

    /**结束时间，通过排课生成*/
    String endDate;

    /**结束周,通过排课生成*/
    Integer endWeek;

    /**班级最小人数*/
    Integer  minStudents;
    /**班级最大人数*/
    Integer maxStudents ;

    /**状态,进行中，结束*/
    String status;

    /**教师--为生成查询实体用，不做数据保存*/
    String teacher;

    /**可节次数*/
    Integer ClassFrequenc;






}
