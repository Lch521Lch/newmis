package com.ef.newmis.controller;


import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.PassToken;
import com.ef.newmis.entity.UserEntity;
import com.ef.newmis.service.impl.TokenServiceImpl;
import com.ef.newmis.service.impl.UserServiceImpl;
import com.ef.newmis.util.JsonExtraTools;
import com.ef.newmis.util.AES;
import com.ef.newmis.util.CrytogramUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:用户Controller
 *
 * @author dirxu
 * @create 2020-03-11
 */
@Slf4j
@RequestMapping("/user")
@RestController

public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    TokenServiceImpl tokenServiceImpl;

    /**
     * 登陆
     * @author dirxu
     */
    @PassToken
    @RequestMapping("/login")
    public JSONObject search(@RequestBody String questJson) {
        log.debug("questJson:\t"+questJson);

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        JsonExtraTools jsonExtraTools = new JsonExtraTools();

        HashMap<String, String> questMap = jsonExtraTools.parseJsonToMap(questJson);


        //请求关键参数，务必确保前台请求的时候要有 userName、passWord关键字段
        String userName = questMap.get("username").trim();



        //解密前台传过来的加密密码
        String passWord = AES.decrypt(questMap.get("password").trim());
        // 将密码解析成后台密码
        passWord = CrytogramUtil.encrypt(passWord, "MD5");

        try {
            UserEntity user = userServiceImpl.login(userName,passWord);

            if (user == null) {
                resultJson.put("msg", "登录失败,用户不存在或密码错误");
                resultJson.put("state", "error");

            } else {
                String token = tokenServiceImpl.getToken(user);
                resultJson.put("state", "success");
                resultJson.put("msg", "登录成功");
                resultJson.put("token", token);
                resultJson.put("cityname", user.getCityname());
                resultJson.put("uername", user.getUsername());

            }


        }catch  (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }


        return resultJson;

    }


}
