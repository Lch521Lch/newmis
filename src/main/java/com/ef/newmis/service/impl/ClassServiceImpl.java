package com.ef.newmis.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.ef.newmis.entity.ClassEntity;
import com.ef.newmis.entity.ClassHisEntity;
import com.ef.newmis.entity.CourseEntity;
import com.ef.newmis.entity.ScheduleEntity;
import com.ef.newmis.mapper.ClassMapper;
import com.ef.newmis.mapper.CourseMapper;
import com.ef.newmis.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static cn.hutool.core.date.DateUtil.weekOfYear;

/**
 * 班级相关方法
 *
 * @author dirxu
 * @date 2020-02-13
 */
@Slf4j
@Service("ClassServiceImpl")
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;


    @Autowired
    CourseMapper courseMapper;



    @Override
    public List<ClassEntity> searchClass(ClassEntity classEntity) throws Exception {


        return classMapper.searchClass(classEntity);

    }

    /**
     * 增加班级信息
     *
     * @param classEntity 班级姓名
     * @return 1：成功，2：重复
     */
    @Override
    public int insertClass(ClassEntity classEntity) throws Exception {

        classEntity.setClassCode(classEntity.getCourseCode() + "_" + classEntity.getTeacher1() + "_" + classEntity.getBeginDate() );

        // 设定开始周
        classEntity.setBeginWeek(weekOfYear(DateUtil.parse(classEntity.getBeginDate())));


        // 产看是否有重复班级记录
        List<ClassEntity> classList = classMapper.checkClass(classEntity.getClassCode());
        // 有重复班级记录
        if (classList.size() > 0) {
            return 2;
        }


        classMapper.insertClass(classEntity);
        return 1;


    }

    @Override
    public void updateClass(ClassEntity classEntity) throws Exception {
        classMapper.updateClass(classEntity);
    }

    @Override
    public void delClass(String courseCode) throws Exception {
        classMapper.delClass(courseCode);
    }

    @Override
    public List<ClassHisEntity> searchClassHis(ClassHisEntity classHisEntity) throws Exception {
        return classMapper.searchClassHis(classHisEntity);
    }

    /**
     * 新增选课历史
     *
     * @param classHisEntity 选课实体    *
     * @throws Exception 数据库相关异常
     */
    @Override
    public void insertClassHis(ClassHisEntity classHisEntity) throws Exception {

        classMapper.insertClassHis(classHisEntity);
    }

    /**
     * 删除选课信息
     *
     * @param classHisEntity 选课历史实体
     * @throws Exception 数据库相关异常
     */
    @Override
    public void delClassHis(ClassHisEntity classHisEntity) throws Exception {
        classMapper.delClassHis(classHisEntity);
    }

    @Override
    public int createSchedule(String classCode) throws Exception {
        // 第一步通过班级编号，获取班级信息，主要获取 课程编号
        ClassEntity classEntity = classMapper.searchClassByCode(classCode);


        List<ScheduleEntity> scheduleList;

        scheduleList = classMapper.searchSchedule(classCode);

        // 如果有课该班级的课程表，返回 0
        if (scheduleList.size() > 0) {
            return 0;
        } else {
            scheduleList = createSchedule(classEntity);

            //生成班级课程表
            classMapper.createSchedule(scheduleList);

            /**
             * 更新班级的结课日期与结课周
             * */

           String endDate =  classMapper.getMaxDate(classCode);

           int endWeek =  DateUtil.weekOfYear( DateUtil.parse(endDate));

           classMapper.updateClassEndDate(classEntity.getClassCode(),endDate,endWeek);

           return scheduleList.size();
        }
    }

    public List<ScheduleEntity> createSchedule(ClassEntity classEntity) throws Exception {

        //定义星期数组
        String[] weekDays = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};

        HashMap<String, String> map = new HashMap<String, String>(2);

        map.put(classEntity.getWeek1(), classEntity.getTime1()+"|"+classEntity.getEndTime1());
        map.put(classEntity.getWeek2(), classEntity.getTime2()+"|"+classEntity.getEndTime2());
        List<ScheduleEntity> scheduleList = new ArrayList<ScheduleEntity>();


        // 定义启动日期
        Date beginDate = DateUtil.parse(classEntity.getBeginDate());

        for (int i = 0; i < classEntity.getClassFrequenc(); i++) {

            while (true) {
                if (map.containsKey(weekDays[DateUtil.dayOfWeek(beginDate) - 1])) {

                    String dateString = DateUtil.format(beginDate, "yyyyMMdd");
                    Integer week =  weekOfYear(DateUtil.parse(dateString));


                    String timeString = map.get(weekDays[DateUtil.dayOfWeek(beginDate) - 1]).split("\\|")[0];

                    String endtimeString = map.get(weekDays[DateUtil.dayOfWeek(beginDate) - 1]).split("\\|")[1];

                    String scheduleId = IdUtil.simpleUUID();
                    //
                    ScheduleEntity schedule = new ScheduleEntity(scheduleId, classEntity.getRoomCode(), classEntity.getClassCode(), classEntity.getTeacher1(), dateString,week, timeString,endtimeString);
                    scheduleList.add(schedule);

                    beginDate = DateUtil.offsetDay(beginDate, 1);
                    break;
                } else {

                    beginDate = DateUtil.offsetDay(beginDate, 1);

                }
            }

//            /** 更新此班级的结束时间，与结束周*/
//
//            String endDate = DateUtil.format(beginDate, "yyyyMMdd");
//
//            int endWeek =  DateUtil.weekOfYear(beginDate);
//
//            classMapper.updateClassEndDate(classEntity.getClassCode(),endDate,endWeek);

        }


        return scheduleList;

    }

    @Override
    public List<ScheduleEntity> searchSchedule(String classCode) throws Exception {
        return classMapper.searchSchedule(classCode);
    }

    @Override
    public List<ScheduleEntity> searchSchedulePrint(String scheduleTeacher,String scheduleDate ,String room ) throws Exception{
        return classMapper.searchSchedulePrint(scheduleTeacher,scheduleDate,room);
    }



    @Override
    public void updateSchedule(ScheduleEntity schedule) throws Exception {

        schedule.setScheduleWeek( weekOfYear(DateUtil.parse(schedule.getScheduleDate())));

        classMapper.updateSchedule(schedule);

        // 更新课程结课日期
        String endDate =  classMapper.getMaxDate(schedule.getClassCode());
        int endWeek =  DateUtil.weekOfYear( DateUtil.parse(endDate));
        classMapper.updateClassEndDate(schedule.getClassCode(),endDate,endWeek);

    }

    @Override
    public Integer checkChooseClass(String studnetNo)  throws Exception {
        return  classMapper.checkChooseClass(studnetNo);
    }

    @Override
    public void autoUpdate()  throws Exception{

        List<ClassEntity> classList = classMapper.autoUpdate ();


        for (ClassEntity classEntity:classList){
            classMapper.updateClassStatus(classEntity.getClassCode());


        }


    }

}




