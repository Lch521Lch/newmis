package com.ef.newmis.test;


import cn.hutool.core.util.IdUtil;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

import static cn.hutool.core.date.DateUtil.weekOfYear;

public class Demo1 {

    public static void main(String[] args)   {

        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();

        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();

        System.out.println(simpleUUID);
        System.out.println(uuid);



        String dateStr = "20180102";
        Date date = DateUtil.parse(dateStr);


        Date date2 = DateUtil.offsetDay(date,1);
        System.out.println( date2 );
        System.out.println(  DateUtil.dayOfWeek(date));

        String format = DateUtil.format(new Date(), "yyyyMMdd");
        System.out.println( format );



        System.out.println( date+"\t"+DateUtil.weekOfYear(date) );

    }
}
