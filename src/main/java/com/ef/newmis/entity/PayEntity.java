package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 缴费实体
 * @author dirxu
 * @date 2020-03-19
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayEntity {

    /**缴费id-UUID
     * */
    String payId;

    /**缴费方式，微信，支付宝等
     * */
    String payWay;




    /**缴费类型，学费，书费
     * */
    String payType;

    /**缴费日期
     * */
    String payDate;

    /**应缴金额
     * */

    String courseCost;

    /**
     * 折扣
     * */
    String courseRate;

    /**实际缴费
     * */
    String courseTotal;

    /**经手员工
     * */
    String payStaff;

    /**学生编号
     * */
    String studentNo;

    String chineseName;
    /**
     * 中文拼音
     */
    String phoneticName;

    /**
     * 备注
     */
    String memo;



}
