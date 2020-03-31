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
public class BookEntity {

    /**教材编号*/
    String bookCode;
    /**教室编号*/
    Integer bookFee;
}
