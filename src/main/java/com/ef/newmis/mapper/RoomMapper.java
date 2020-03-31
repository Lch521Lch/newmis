package com.ef.newmis.mapper;

import com.ef.newmis.entity.BookEntity;
import com.ef.newmis.entity.RoomEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;


/**
 * 教师接口
 * @author dirxu
 * @date 2020-02-14
 */
public interface RoomMapper {
    /**
     * 查找所有教室
     *
     * @throws   Exception 数据库相关异常
     * @return 查询教室结果集
     */
    List<RoomEntity> showAllRooms()  throws Exception;


    /**
     * 查找所有教室
     *
     * @throws   Exception 数据库相关异常
     * @return 查询教材结果集
     */
    List<BookEntity> showAllBooks()  throws Exception;








}
