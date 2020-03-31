package com.ef.newmis.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ef.newmis.annotation.PassToken;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.UserEntity;
import com.ef.newmis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author drixu
 * @date 2018-07-08 20:41
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
       // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");




        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {

            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {

            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }


                // 获取 token 中的 userName
                String userName;
                try {
                    userName =JWT.decode(token).getClaim("username").asString();

                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserEntity user = userServiceImpl.findUserByUserName(userName);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }



                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    DecodedJWT jwt = jwtVerifier.verify(token);


                    Date date = jwt.getExpiresAt();

                    Calendar calendar = Calendar.getInstance();
                    Date now = calendar.getTime();

                    // 超时，抛出 超时异常    ！
                    if (date.before(now)){
                        throw new RuntimeException("overTime");

                    }

                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }


                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {




    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

