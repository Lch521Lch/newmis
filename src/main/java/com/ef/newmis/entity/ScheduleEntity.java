package com.ef.newmis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 班级 历史，就是选课历史
 * @author dirxu
 * @date 2020-03-16
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ScheduleEntity {

    /**
     * 课程表id
     */
    String scheduleId;
    /**
     * 教室代码
     */
    String roomCode;
    /**
     * 班级代码
     */
    String classCode;
    /**
     * 教师1
     */
    String scheduleTeacher;
    /**
     * 上课星期1
     */
    String scheduleDate;

    /**
     * 上课周
     */
    Integer scheduleWeek;
    /**
     * 上课时间1
     */
    String scheduleTime;

    /**
     * 下课时间1
     */
    String scheduleEndtime;
//    ScheduleEntity(String scheduleId, String roomCode, String classCode, String scheduleTeacher, String scheduleDate, String scheduleTime) {
//        this.scheduleId = scheduleId;
//        this.roomCode = roomCode;
//
//        this.classCode = classCode;
//        this.scheduleTeacher = scheduleTeacher;
//        this.scheduleDate = scheduleDate;
//        this.scheduleTime = scheduleTime;
//
//
//    }

}
