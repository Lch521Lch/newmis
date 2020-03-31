package com.ef.newmis.mapper;

import com.ef.newmis.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;


/**
 * 用户信息接口
 * @author dirxu
 * @date 2020-03-11
 */
public interface UserMapper {
    /**
     * 查找用户信息
     *
     * @throws   Exception 数据库相关异常
     * @param userName 用户名
     * @param passWord 密码
     * @return 查询用户信息
     */
    UserEntity login(@Param("userName") String userName, @Param("passWord") String passWord )  throws Exception;

    /**
     * 查找用户信息
     *
     * @param userName 用户名
     * @throws   Exception 数据库相关异常
     * @return 查询用户信息
     */

    UserEntity  findUserByUserName(@Param("userName") String userName) throws Exception;








}
