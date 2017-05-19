/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.util
 * Type:    FastDFSHeartBeat
 * Author:  hefengwen
 * Date:    2017-01-07 20:38:46
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yckj.school.common.constant.Constants;

/**
 * 
 * @ClassName: HeartBeat
 * @Description: 连接池定时器设置
 * @author mr_smile2014 mr_smile2014@xxxx.com
 * @date 2015年1月14日 下午2:21:29
 * 
 */
public class HeartBeat {
    private static final Logger logger = LoggerFactory.getLogger(HeartBeat.class);
    
    /** fastdfs连接池 */
    private ConnectionPool pool = null;
    /** 小时毫秒数 */
    public static int ahour = 1000 * 60 * 60 * 1;

    public HeartBeat(ConnectionPool pool) {  
        this.pool = pool;  
    }

    /**
     * 
     * @Description: 定时执行任务，检测当前的空闲连接是否可用，如果不可用将从连接池中移除
     * 
     */
    public void beat() {
        logger.info("[FastDFS 心跳任务方法（beat）]");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logger.info("[心跳任务方法（beat）][Description:对idleConnectionPool中的TrackerServer进行监测]");
                LinkedBlockingQueue<TrackerServer> idleConnectionPool = pool.getIdleConnectionPool();
                TrackerServer ts = null;
                for (int i = 0; i < idleConnectionPool.size(); i++) {
                    try {
                        ts = idleConnectionPool.poll(Constants.FASTDFS_CLIENT_WAIT_TIME, TimeUnit.SECONDS);
                        if (ts != null) {
                            ProtoCommon.activeTest(ts.getSocket());
                            idleConnectionPool.add(ts);
                        }
                        else {
                            /** 代表已经没有空闲长连接 */
                            break;
                        }
                    }
                    catch (Exception e) {
                        /** 发生异常,要删除，进行重建 */
                        logger.error("[心跳任务方法（beat）][异常：当前连接已不可用将进行重新获取连接]");
                        pool.drop(ts);
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, ahour, ahour);
    }
}
