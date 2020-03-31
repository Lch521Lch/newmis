package com.ef.newmis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 学生实体
 * @author dirxu
 * @date 2020-02-11
 *  *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    /**
     * ID
     */
    Integer id;
    /**
     * 学号
     */
    String studentNo;
    /**
     * 英文名
     */
    String englishName;
    /**
     * 中文名
     */
    String chineseName;
    /**
     * 中文拼音
     */
    String phoneticName;

    /**
     * 联系电话
     */
    String telNo;

    /**
     * 性别
     */
    String sex;
    /**
     * 生日
     */
    String birthday;
    /**
     * 缴费日期
     */
    String payDate;
    /**
     * 缴费级别
     */
    String payLevel;
    /**
     * 课程记录
     */
    String courseHistory;
    /**
     * 建档人
     */
    String createPerson;
    /**
     * 建档日期
     */
    String createDate;
    /**
     * 修改日期
     */
    String modifyDate;
    /**
     * 学生来源
     */
    String studentSource;
    /**
     * 备注
     */
    String memo;
    /**
     * 历史备注
     */
    String memoHistory;


}
