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
public class AbsentEntity {

    /**
     * 课程表id
     */
    String scheduleId;

    /**
     * 学号
     */
    String studentNo;

    /**
     * 是否出勤
     * */
    Boolean  absent;


}
