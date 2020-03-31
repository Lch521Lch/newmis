package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 课程实体
 * @author dirxu
 * @date 2020-02-11
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseEntity {
    String id;
    /**课程代码*/
    String courseCode;

    /**课程类型*/
    String courseType;
    /**课程描述*/

    String courseDes;
    /**课程课时次数*/
    Integer courseDuration ;

    /**课程周期*/
    Integer courseCycle;

    /**课程价格*/
    Integer  courseCost;

    /**课时单价*/
    Integer  univalence;

}
