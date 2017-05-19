/*******************************************************************************
 * Project: school-service
 * Package: com.yckj.school.util
 * Type:    ConnectionPool
 * Author:  hefengwen
 * Date:    2016-12-29 15:06:42
 *
 * Copyright (c) 2016 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hefengwen
 */
public class FastDFSConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(FastDFSConnectionPool.class);
    
    /**
     * 正在使用连接
     */
    private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
    /**
     * 空闲连接
     */
    private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;
    
    private static FastDFSConnectionPool instance = null;
    
    private int initSize;
    
    private int maxSize;
    
    private String trackerHost;
    
    private int trackerPort;
    
    private Object obj = new Object();

    private FastDFSConnectionPool() {
        
    }
    
    public static synchronized FastDFSConnectionPool getInstance(int initSize,int maxSize,String trackerHost,int trackerPort,int connectTimeout,int networkTimeout,String charset,String secreKey){
        if(instance != null)
            return instance;
        instance = new FastDFSConnectionPool();
        instance.init(initSize,maxSize,trackerHost,trackerPort,connectTimeout,networkTimeout,charset,secreKey);
        return instance;
    }
    /**
     * 初始化连接池
     */
    private  void init(int initSize,int maxSize,String trackerHost,int trackerPort,int connectTimeout,int networkTimeout,String charset,String secreKey ) {
        logger.info("FastDFSConnectionPool init start ... ...");
        busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
        idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(initSize);
        
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
        this.trackerHost = trackerHost;
        this.trackerPort = trackerPort;
        
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
            if(trackerServer==null){
                logger.error("getConnection return null");
            }
            for (int i = 0; i < initSize; i++) {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
                if(storageServer==null){
                    logger.error("getStoreStorage return null");
                }
                StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
                ProtoCommon.activeTest(new Socket(trackerHost,trackerPort));
                idleConnectionPool.add(client1);
            }
            logger.info("idleConnectionPool initsize is:{}",idleConnectionPool.size());
        }
        catch (IOException e) {
            logger.error("",e);
        }
        finally {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                }
                catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
        logger.info("FastDFSConnectionPool init end ... ...");
    }

    // 1. pop one connection from the idleConnectionPool,
    // 2. push the connection into busyConnectionPool;
    // 3. return the connection
    // 4. if no idle connection, do wait for wait_time seconds, and check again
    /**
     * 从空闲连接池中获取连接对象，放入正在使用连接池
     */
    public StorageClient1 checkout(int waitTimes) throws InterruptedException {
        logger.info("checkout idle size:"+idleConnectionPool.size()+";busy size:"+busyConnectionPool.size());
        StorageClient1 client1 = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
        //当前有可用连接，直接返回
        if(client1 != null){
            busyConnectionPool.put(client1, obj);
            return client1;
        }
        //当前连接已超最大连接数，直接返回null
        if(busyConnectionPool.size()>=maxSize)
            return null;
        //当前连接未到最大，创建新连接
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
           
            trackerServer = trackerClient.getConnection();
            if(trackerServer==null){
                logger.error("getConnection return null");
            }
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if(storageServer==null){
                logger.error("getStoreStorage return null");
            }
            StorageClient1 newClient = new StorageClient1(trackerServer, storageServer);
            ProtoCommon.activeTest(new Socket(trackerHost,trackerPort));
            busyConnectionPool.put(newClient, obj);
            logger.debug("create new connection,current connection size:{}",busyConnectionPool.size());
            return newClient;
        }
        catch (IOException e) {
            logger.error("",e);
            return null;
        }
        finally {
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

    // 1. pop the connection from busyConnectionPool;
    // 2. push the connection into idleConnectionPool;
    // 3. do nessary cleanup works.
    /**
     * 使用完后放回空闲连接池
     */
    public void checkin(StorageClient1 client1) {
        logger.info("checkin client");
        logger.info("begin idle size:"+idleConnectionPool.size()+";busy size:"+busyConnectionPool.size());
        if (busyConnectionPool.remove(client1) != null) {
            idleConnectionPool.add(client1);
            while(idleConnectionPool.size()>initSize){
                idleConnectionPool.poll();
                logger.debug("remove idle connection,current idle connection size:{}",idleConnectionPool.size());
            }
        }
        logger.info("end idle size:"+idleConnectionPool.size()+";busy size:"+busyConnectionPool.size());
    }

    // so if the connection was broken due to some erros (like
    // : socket init failure, network broken etc), drop this connection
    // from the busyConnectionPool, and init one new connection.
    /**
     * 连接出错时，丢弃连接
     */
    public void drop(StorageClient1 client1) {
        busyConnectionPool.remove(client1);
    }
}
