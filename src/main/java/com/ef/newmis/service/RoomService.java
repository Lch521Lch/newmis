package com.ef.newmis.service;

import com.ef.newmis.entity.BookEntity;
import com.ef.newmis.entity.RoomEntity;

import java.sql.SQLException;
import java.util.List;
/**
 * 教室相关接口信息
 * @author dirxu
 * @date 2020-02-14
 */
public interface RoomService {

    /**
     * 获得所有教室信息
     *
     * @return 教室信息结果集
     * @throws   Exception 数据库相关异常
     */
    List<RoomEntity> showAllRooms()  throws Exception;


    /**
     * 查找所有教材
     *
     * @throws   Exception 数据库相关异常
     * @return 查询教材结果集
     */
    List<BookEntity> showAllBooks()  throws Exception;
}
