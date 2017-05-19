package com.yckj.school.service;

import org.csource.fastdfs.FileInfo;


public interface FastDFSService {
	/**
	 * 上传文件到文件服务器
	 * @param bytes 文件字节数组
	 * @param fileName 文件名
	 * @return
	 */
	String uploadFile(byte[] bytes,String fileName);
	/**
	 * 上传分段文件到文件服务器
	 * @param bytes 文件字节数组
	 * @param fileName 文件名
	 * @return
	 */
	String uploadAppendFile(byte[] bytes,String fileName);
	/**
	 * 上传分段文件到文件服务器指定文件
	 * @param bytes
	 * @param fileId
	 * @return 0 成功 !0 失败
	 */
	int appendFile(byte[] bytes,String fileId);
	/**
	 * 从文件服务器下载文件
	 * @param fileId 文件ID
	 * @return
	 */
	byte[] downloadFile(String fileId);
	/**
	 * 从文件服务器下载文件的指定大小
	 * @param fileId 文件ID
	 * @return
	 */
	byte[] downloadFile(String fileId,long offset,long length);
	/**
	 * 删除文件服务器指定文件
	 * @param fileId 文件ID
	 * @return 0:删除成功 其他:删除失败
	 */
	int deleteFile(String fileId);
	/**
	 * 获取文件信息
	 * @param fileId 文件ID
	 * @return 
	 */
	FileInfo getFileInfo(String fileId);
	/**
	 * 获取文件所在存储服务器
	 * @param fileId 文件ID
	 * @return http://ip:port/fileId
	 */
	String getFileStorage(String fileId);
}
