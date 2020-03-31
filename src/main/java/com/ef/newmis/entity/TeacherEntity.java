package com.ef.newmis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 教师实体
 * @author dirxu
 * @date 2020-02-11
 *  *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TeacherEntity {
    String teacherId;
    /**教师姓名*/
    String teacherName;
    /**教师类型*/
    String teacherType;
    /**备注*/
    String memo;
}
