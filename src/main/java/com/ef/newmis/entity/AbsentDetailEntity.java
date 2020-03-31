package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 教材实体
 * @author dirxu
 * @date 2020-03-19
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsentDetailEntity {

    /**
     * 班级编号
     */
    String classCode;

    /**
     * 课程表日期
     */
    String scheduleDate;

    /**
     * 课程表时间
     * */
    String  scheduleTime;


    /**
     * 中文名
     * */
    String  chineseName;


    /**
     * 中文拼音
     * */
    String  phoneticName;

    /**
     * 课时单价
     */

    Integer univalence;

    /**
     * 出勤
     * */
    String  absent;



}
