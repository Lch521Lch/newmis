package com.ef.newmis.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ef.newmis.entity.UserEntity;
import com.ef.newmis.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 教室相关方法
 * @author dirxu
 * @date 2020-02-14
 */
@Slf4j
@Service("TokenServiceImpl")
public class TokenServiceImpl implements TokenService  {


    @Override
    public String getToken(UserEntity user) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //日期转字符串
        Calendar calendar = Calendar.getInstance();
        //特定时间的后
        calendar.add(Calendar.MINUTE,30 );
        Date date = calendar.getTime();



        Map<String, Object> headerClaims = new HashMap();

        headerClaims.put("role", "guest");
        headerClaims.put("username", user.getUsername());




        String token = JWT.create().withAudience( user.getId() )
                .withClaim("key",user.getRole())
                //.withClaim("username", user.getCityname())
                .withClaim("username", user.getUsername())
                .withHeader(headerClaims)
                .withExpiresAt(date)
                // 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));
                // 以 password 作为 token 的密钥
        return token;

    }

}
