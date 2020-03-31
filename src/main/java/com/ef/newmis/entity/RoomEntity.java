package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 教室实体
 * @author dirxu
 * @date 2020-02-11
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    String id;
    /**楼层*/
    String floor;
    /**教室编号*/
    String roomCode;
}
