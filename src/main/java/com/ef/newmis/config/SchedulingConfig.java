package com.ef.newmis.config;

import com.ef.newmis.service.impl.ClassServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;


/**
 * @author dirxu
 * @discib 定时任务配置类, 通过 注释@EnableScheduling，进行自行定时任务的启动和关闭
 * @myblog
 * @create 2019年10月28日
 */
@Slf4j
@Configuration
@EnableScheduling
public class SchedulingConfig {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH::mm::ss");
    @Autowired
    ClassServiceImpl classServiceImpl;

    /**
     * @Scheduled(fixedRate = 5000)
     * public void proFixCurrentTime() {
     * System.out.println();
     * <p>
     * log.info("每5秒钟执行一次：" + dateFormat.format(new Date()));
     * }
     */

    /*
     * second（秒）, minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
     *  例子：
     * 【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     * 【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
       【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次
     */

    @Scheduled(cron = "0 1 2 * * ?")
    public void scheduler() {
        try {

            classServiceImpl.autoUpdate();

        } catch (Exception e) {
           log.error(e.toString());
        }
    }

}

