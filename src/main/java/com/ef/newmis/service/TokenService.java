package com.ef.newmis.service;

import com.ef.newmis.entity.UserEntity;import com.ef.newmis.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Token接口信息
 * @author dirxu
 * @date 2020-03-11
 */
public interface TokenService {

    /**
     * 获取token
     *
     * @return token
     * @throws   Exception 数据库相关异常
     */

      String getToken(UserEntity user) throws Exception;




}
