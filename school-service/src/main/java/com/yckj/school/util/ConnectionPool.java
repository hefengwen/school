/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.util
 * Type:    ConnectionPool
 * Author:  hefengwen
 * Date:    2017-01-07 20:51:23
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fastdfs连接池
 * 
 * @author hefengwen
 */
public class ConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    /** 
     * 空闲的连接池 
     */
    private LinkedBlockingQueue<TrackerServer> idleConnectionPool = null;
    /** 
     * 使用的连接池 
     */
    private ConcurrentHashMap<StorageClient1, TrackerServer> busyConnectionPool = null;
    /** 
     * 连接池默认初始连接数
     */
    private long initSize = 10;
    /** 
     * 连接池默认最大连接数
     */
    private long maxSize = 30;
    private static ConnectionPool instance = null;
    private ConnectionPool() {
        
    }
    public LinkedBlockingQueue<TrackerServer> getIdleConnectionPool() {
        return idleConnectionPool;
    }
    public static synchronized ConnectionPool getInstance(int initSize,int maxSize,String trackerHost,int trackerPort,int connectTimeout,int networkTimeout,String charset,String secreKey){
        if(instance != null)
            return instance;
        instance = new ConnectionPool();
        instance.init(initSize,maxSize,trackerHost,trackerPort,connectTimeout,networkTimeout,charset,secreKey);
        return instance;
    }
    /**
     * 初始化连接池
     */
    private  void init(int initSize,int maxSize,String trackerHost,int trackerPort,int connectTimeout,int networkTimeout,String charset,String secreKey ) {
        logger.info("FastDFSConnectionPool init start ... ...");
        busyConnectionPool = new ConcurrentHashMap<StorageClient1, TrackerServer>();
        idleConnectionPool = new LinkedBlockingQueue<TrackerServer>(initSize);
        
        InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        trackerServers[0] = new InetSocketAddress(trackerHost, trackerPort);
        ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));
        ClientGlobal.setG_connect_timeout(connectTimeout);
        ClientGlobal.setG_network_timeout(networkTimeout);
        ClientGlobal.setG_anti_steal_token(false);
        ClientGlobal.setG_charset(charset);
        ClientGlobal.setG_secret_key(secreKey);
        this.maxSize = maxSize;
        this.initSize = initSize;
        /** 往线程池中添加默认大小的线程 */
        for (int i = 0; i < initSize; i++) {
            createTrackerServer();
        }
        /** 注册心跳 */
        HeartBeat beat = new HeartBeat(this);
        beat.beat();
        logger.info("FastDFSConnectionPool init end ... ...");
    }

    /**
     * 
     * @Description: 创建TrackerServer,并放入空闲连接池
     * 
     */
    public void createTrackerServer() {
        int flag = 1;
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            while (trackerServer == null && flag < 5) {
                logger.info("[创建TrackerServer(createTrackerServer)][第" + flag + "次重建]");
                flag++;
                trackerServer = trackerClient.getConnection();
            }
            ProtoCommon.activeTest(trackerServer.getSocket());
            idleConnectionPool.add(trackerServer);
        }
        catch (Exception e) {
            logger.error("[创建TrackerServer(createTrackerServer)][异常：{}]", e);
        }
    }

    /**
     * @throws InterruptedException 
     * @Description: 获取空闲连接 
     * 1).在空闲池（idleConnectionPool)中弹出一个连接；
     * 2).把该连接放入忙碌池（busyConnectionPool）中; 
     * 3).返回 connection
     * 4).如果没有idle connection, 等待 wait_time秒, and check again
     */
    public StorageClient1 checkout(int waitTimes) throws InterruptedException {
        logger.info("checkout idle size:" + idleConnectionPool.size() + ";busy size:" + busyConnectionPool.size());
        TrackerServer trackerServer = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
        // 当前无可用连接且未达到最大连接，创建新连接
        if (trackerServer == null&&busyConnectionPool.size() < maxSize) {
            createTrackerServer();
            trackerServer = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
            if (trackerServer == null) {
                logger.error("[获取空闲连接(checkout)-error][error:获取连接超时（" + waitTimes + "s）]");
                return null;
            }
        }else if(trackerServer == null){
            logger.info("连接已到最大... ...");
            return null;
        }
        logger.info("[获取空闲连接(checkout)][获取空闲连接成功]");
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        busyConnectionPool.put(client1, trackerServer);
        return client1;

    }
    /**
     * @throws InterruptedException 
     * @Description: 获取空闲连接 
     */
    public TrackerServer checkoutTracker(int waitTimes) throws InterruptedException {
        logger.info("checkoutTracker idle size:" + idleConnectionPool.size() + ";busy size:" + busyConnectionPool.size());
        return idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);

    }

    /**
     * @Description: 释放繁忙连接 
     * 1.如果空闲池的连接小于最小连接值，就把当前连接放入idleConnectionPool；
     * 2.如果空闲池的连接等于或大于最小连接值，就把当前释放连接丢弃；
     * @param client1
     *            需释放的连接对象
     */
    public void checkin(StorageClient1 client1) {
        logger.info("[释放当前连接(checkin)][prams:" + client1 + "] ");
        if (client1 != null) {
            TrackerServer trackerServer = busyConnectionPool.remove(client1);
            if (idleConnectionPool.size() < initSize) {
                idleConnectionPool.add(trackerServer);
            } else {
                try {
                    trackerServer.close();
                }
                catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }
    /**
     * @Description: 释放繁忙连接 
     */
    public void checkinTracker(TrackerServer tracker) {
        logger.info("[释放当前连接(checkinTracker)][prams:" + tracker + "] ");
        if (tracker != null) {
            if (idleConnectionPool.size() < initSize) {
                idleConnectionPool.add(tracker);
            } else {
                try {
                    tracker.close();
                }
                catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }

    /**
     * @Description: 删除不可用的连接，并把当前连接数减一（调用过程中trackerServer报异常，调用一般在finally中）
     * @param trackerServer
     */
    public void drop(StorageClient1 client1) {
        logger.info("[删除不可用连接方法(drop)][parms:" + client1 + "] ");
        if (client1 != null) {
            TrackerServer trackerServer = busyConnectionPool.remove(client1);
            try {
                trackerServer.close();
            }
            catch (IOException e) {
                logger.error("",e);
            }
        }
    }
    public void drop(TrackerServer trackerServer) {
        logger.info("[删除不可用连接方法(drop)][parms:" + trackerServer + "] ");
        if (trackerServer != null) {
            try {
                trackerServer.close();
            }
            catch (IOException e) {
                logger.error("",e);
            }
        }
    }
}
