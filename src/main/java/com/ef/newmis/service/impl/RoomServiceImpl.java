package com.ef.newmis.service.impl;

        import com.ef.newmis.entity.BookEntity;
        import com.ef.newmis.entity.RoomEntity;
        import com.ef.newmis.mapper.RoomMapper;
        import com.ef.newmis.service.RoomService;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.sql.SQLException;
        import java.util.List;

/**
 * 教室相关方法
 * @author dirxu
 * @date 2020-02-14
 */
@Slf4j
@Service("RoomServiceImpl")
public class RoomServiceImpl implements RoomService  {
    @Autowired
    RoomMapper roomMapper;

    @Override
    public List<RoomEntity> showAllRooms()  throws Exception {
        return roomMapper.showAllRooms();
    }

    @Override
    public List<BookEntity> showAllBooks()  throws Exception{
        return roomMapper.showAllBooks();
    }

}
