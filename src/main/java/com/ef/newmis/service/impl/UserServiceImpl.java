package com.ef.newmis.service.impl;

import com.ef.newmis.entity.UserEntity;
import com.ef.newmis.mapper.UserMapper;
import com.ef.newmis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户相关方法
 * @author dirxu
 * @date 2020-02-14
 */
@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService  {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity login(String userName,String passWord)  throws Exception {

        return  userMapper.login(userName,passWord);
    }

    @Override
    public UserEntity findUserByUserName(String userName)  throws Exception {

        return  userMapper.findUserByUserName(userName);
    }

}
