package com.ef.newmis.service;

import com.ef.newmis.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户接口信息
 * @author dirxu
 * @date 2020-03-11
 */
public interface UserService {

    /**
     * 查询用户信息
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return 查询用户
     * @throws   Exception 数据库相关异常
     */
    UserEntity login(String userName,String passWord)  throws Exception;
    /**
     * 根据用户名，在数据库中查询用户信息
     *
     * @param  userName 用户名
     * @return 查询用户
     * @throws   Exception 数据库相关异常
     */
    UserEntity findUserByUserName(String userName)  throws Exception;



}
