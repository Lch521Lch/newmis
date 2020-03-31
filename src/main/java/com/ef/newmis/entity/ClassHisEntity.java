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

public class ClassHisEntity {


    /**班级代码*/
    String classCode;
    /**学生代码*/
    /**
     * 学号
     */
    String studentNo;

    String vdate ;

    String status ;





}
