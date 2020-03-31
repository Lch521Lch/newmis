package com.ef.newmis.service.impl;

import com.ef.newmis.entity.CourseEntity;
import com.ef.newmis.mapper.CourseMapper;
import com.ef.newmis.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 课程相关方法
 * @author dirxu
 * @date 2020-02-14
 */
@Slf4j
@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<CourseEntity> showAllCourse() throws Exception {
            return courseMapper.showAllCourse();
    }


    @Override
    public void updateCourse(CourseEntity courseEntity) throws Exception {
            courseMapper.updateCourse(courseEntity);
    }



    @Override
    public  CourseEntity searchCourse(String courseCode ) throws Exception{
        return  courseMapper.searchCourse(courseCode);
    }

}
