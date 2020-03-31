package com.ef.newmis.controller;


import com.alibaba.fastjson.JSONObject;
import com.ef.newmis.annotation.UserLoginToken;
import com.ef.newmis.entity.BookEntity;
import com.ef.newmis.entity.RoomEntity;
import com.ef.newmis.service.impl.RoomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * 描述:教室信息Controller
 *
 * @author dirxu
 * @create 2020-02-14
 */
@Slf4j
@RequestMapping("/room")
@RestController

public class RoomController {

    @Autowired
    RoomServiceImpl roomServiceImpl;

    /**
     * 教室信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/search")
    public JSONObject search() {

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        try {
            /**查询教室信息*/
            List<RoomEntity> roomList = roomServiceImpl.showAllRooms();

            /**构建前台返回结果集*/
            if (roomList.size() == 0 || roomList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关教室信息。");
                resultJson.put("state", "warning");

            } else {
                log.debug("教室信息检索成功");
                resultJson.put("total", roomList.size());
                resultJson.put("list", roomList);
                resultJson.put("msg", "教室信息检索成功");
                resultJson.put("state", "success");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


    /**
     * 教材信息查询
     * @author dirxu
     */
    @UserLoginToken
    @RequestMapping("/book")
    public JSONObject book() {

        // 初始化返回json
        JSONObject resultJson = new JSONObject();

        try {
            /**查询教材信息*/
            List<BookEntity> bookList = roomServiceImpl.showAllBooks();

            /**构建前台返回结果集*/
            if (bookList.size() == 0 || bookList.isEmpty()) {
                resultJson.put("msg", "没有查询到相关教材信息。");
                resultJson.put("state", "warning");

            } else {
                log.debug("教材信息检索成功");
                resultJson.put("total", bookList.size());
                resultJson.put("list", bookList);
                resultJson.put("msg", "教室信息检索成功");
                resultJson.put("state", "success");
            }
        } catch (Exception e) {
            log.error(e.toString());
            resultJson.put("msg", "数据库连接失败");
            resultJson.put("state", "error");
        }
        return resultJson;

    }


}
