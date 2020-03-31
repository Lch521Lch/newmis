package com.ef.newmis.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 用户实体
 * @author dirxu
 * @date 2020-03-11
 *  *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    String Id;
    String username;
    String password;
    String cityname;
    String role;
}
