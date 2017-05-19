package com.yckj.school.service.impl;

import javax.annotation.PostConstruct;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.CommonUtils;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.FastDFSService;
import com.yckj.school.util.ConnectionPool;
@Service
public class FastDFSServiceImpl implements FastDFSService{
	
	private static final Logger logger = LoggerFactory.getLogger(FastDFSServiceImpl.class);
	
	@Value("${fastdfs.initSize}")
	private int initSize;
	@Value("${fastdfs.maxSize}")
	private int maxSize;
	@Value("${fastdfs.trackerHost}")
	private String trackerHost;
	@Value("${fastdfs.trackerPort}")
	private int trackerPort;
	@Value("${fastdfs.connectTimeout}")
	private int connectTimeout;
	@Value("${fastdfs.networkTimeout}")
	private int networkTimeout;
	@Value("${fastdfs.charset}")
	private String charset;
	@Value("${fastdfs.secreKey}")
    private String secreKey;
	
	@Value("${fastdfs.trackerNginxPort}")
    private String trackerNginxPort;
	
	private ConnectionPool connectionPool = null;
	
	@PostConstruct
	private void init(){
	    connectionPool = ConnectionPool.getInstance(initSize, maxSize, trackerHost, trackerPort, connectTimeout, networkTimeout, charset, secreKey);
	}
	
	@Override
	public String uploadFile(byte[] bytes, String fileName) {
		logger.info("FastDFSServiceImpl uploadfile start ... ... filename:"+fileName);
		StorageClient1 client = null;
		try{
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
		        throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.upload_file1(bytes,CommonUtils.getFileExt(fileName),null);
		}catch(Exception ex){
			logger.error("",ex);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_upload_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}
	@Override
	public String uploadAppendFile(byte[] bytes,String fileName){
		logger.info("FastDFSServiceImpl uploadAppendFile start ... ... filename:"+fileName);
		StorageClient1 client = null;
		try{
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.upload_appender_file1(bytes,CommonUtils.getFileExt(fileName),null);
		}catch(Exception ex){
			logger.error("",ex);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_upload_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}
	@Override
	public int appendFile(byte[] bytes,String fileId){
		logger.info("FastDFSServiceImpl appendFile start ... ... fileId:"+fileId);
		StorageClient1 client = null;
		try{
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.append_file1(fileId,bytes);
		}catch(Exception ex){
			logger.error("",ex);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_upload_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}

	@Override
	public byte[] downloadFile(String fileId) {
		logger.info("FastDFSServiceImpl downloadFile start ... ... fileId:"+fileId);
		StorageClient1 client = null;
		try {
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.download_file1(fileId);
		} catch (Exception e) {
			logger.error("",e);
			if(client!=null)
			    connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_download_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}
	@Override
	public byte[] downloadFile(String fileId,long offset,long length) {
		logger.info("FastDFSServiceImpl downloadFile start ... ... fileId:"+fileId);
		StorageClient1 client = null;
		try {
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.download_file1(fileId,offset,length);
		} catch (Exception e) {
			logger.error("",e);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_download_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}

	@Override
	public int deleteFile(String fileId) {
		logger.info("FastDFSServiceImpl deleteFile start ... ... fileId:"+fileId);
		StorageClient1 client = null;
		try {
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.delete_file1(fileId);
		} catch (Exception e) {
			logger.error("",e);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_delete_file, null);
		} finally {
            if(client != null)
                connectionPool.checkin(client);
        }
	}
	
	@Override
	public FileInfo getFileInfo(String fileId) {
		logger.info("FastDFSServiceImpl getFileInfo start ... ... fileId:"+fileId);
		StorageClient1 client = null;
		try {
		    client = connectionPool.checkout(Constants.FASTDFS_CLIENT_WAIT_TIME);
		    if(client==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
			return client.get_file_info1(fileId);
		} catch (Exception e) {
			logger.error("",e);
			if(client!=null)
                connectionPool.drop(client);
			throw new SchoolException(SchoolErrorType.err_query_file, null);
		} finally {
		    if(client != null)
		        connectionPool.checkin(client);
        }
	}
	@Override
	public String getFileStorage(String fileId) {
	    logger.info("FastDFSServiceImpl getFileStorage start ... ... fileId:"+fileId);
        TrackerServer tracker = null;
        try {
            tracker = connectionPool.checkoutTracker(Constants.FASTDFS_CLIENT_WAIT_TIME);
            if(tracker==null)
                throw new SchoolException(SchoolErrorType.err_connection_timeout, null);
            String address = "http://"+tracker.getInetSocketAddress().getAddress().getHostAddress() + ":" + trackerNginxPort+"/";
            logger.info("当前可用tracker:"+address);
            return address;
        } catch (Exception e) {
            logger.error("",e);
            if(tracker!=null)
                connectionPool.drop(tracker);
            throw new SchoolException(SchoolErrorType.err_query_file, null);
        } finally {
            if(tracker != null)
                connectionPool.checkinTracker(tracker);
        }
	}
}
