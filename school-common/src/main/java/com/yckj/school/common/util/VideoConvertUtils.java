/*******************************************************************************
 * Project: school-common
 * Package: com.yckj.school.common.util
 * Type:    VideoConvertUtils
 * Author:  hefengwen
 * Date:    2017-02-03 16:59:07
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hefengwen
 */
public class VideoConvertUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(VideoConvertUtils.class);
    
    private static final String FFMEPG_WIN = "e:\\ffmpeg\\ffmpeg";
    private static final String FFMEPG_LINUX = "/usr/local/ffmpeg/bin/ffmpeg";
    
    public static String videoConvert(String oldFilePath){
        if (!checkfile(oldFilePath)) {  
            logger.info(oldFilePath + " is not file");  
            return null;  
        }  
        int type = checkContentType(oldFilePath);  
        String newFilePath = null;  
        if (type == 0) {  
            logger.info("当前转换文件:"+oldFilePath);
            newFilePath = processFLV(oldFilePath);// 直接将文件转为flv文件  
        }
        return newFilePath;  
    }
    private static boolean checkfile(String path) {  
        File file = new File(path);  
        if (!file.isFile()) {  
            return false;  
        }  
        return true;  
    }  
    private static int checkContentType(String fileName) {  
        String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();  
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
        if (type.equals("avi")) {  
            return 0;  
        } else if (type.equals("mpg")) {  
            return 0;  
        } else if (type.equals("wmv")) {  
            return 0;  
        } else if (type.equals("3gp")) {  
            return 0;  
        } else if (type.equals("mov")) {  
            return 0;  
        } else if (type.equals("mp4")) {  
            return 0;  
        } else if (type.equals("asf")) {  
            return 0;  
        } else if (type.equals("asx")) {  
            return 0;  
        } else if (type.equals("flv")) {  
            return 0;  
        }  
        return 9;  
    }  
    
    
    
    private static String processFLV(String oldFilePath) {  
        // 文件命名  
        List<String> command = new ArrayList<String>();  
        setFfmpeg(command);
        command.add("-i");  
        command.add(oldFilePath);  
        command.add("-y");  
        command.add("-ab");  
        command.add("56");  //音频比特率
        command.add("-ar");  
        command.add("22050"); //音频采样率
        command.add("-qscale");  
        command.add("8");  //视频质量(0.01-255)越低质量越好
        command.add("-r");  
        command.add("15"); //每秒帧数（15或29.97）
        command.add("-s");  
        command.add("600x500"); //视频分辨率 如640x480 
        String newFilePath = oldFilePath.substring(0,oldFilePath.lastIndexOf(".") + 1)+"flv";
        command.add(newFilePath);  
        int rst = exec(command);
        if(rst!=0)
            return null;
        return newFilePath;  
    }  
    /**
     * 设置ffmpeg
     */
    public static void setFfmpeg(List<String> command){
        String os = System.getProperty("os.name"); 
        if(os.startsWith("Win")){
            command.add(FFMEPG_WIN);  
        }else{
            command.add(FFMEPG_LINUX);  
        }
    }
    /**
     * 执行指令
     */
    public static int exec(List<String> command){
        try {  
            ProcessBuilder builder = new ProcessBuilder();    
            builder.command(command);  
            builder.redirectErrorStream(true);  
            long start = System.currentTimeMillis();
            Process proc = builder.start();  
            BufferedReader stdout = new BufferedReader(  
                    new InputStreamReader(proc.getInputStream()));  
//            String line;  
            while (stdout.readLine()!= null) {  
//                logger.info("line:"+line);
//                Matcher m = Pattern.compile("Duration: //w+://w+://w+").matcher(line);   
//                while (m.find())   
//                {  
//                     String msg = m.group();  
//                     msg = msg.replace("Duration: ", "");  
//                     logger.info("msg:"+msg);
////                     runtime = TimeUtil.runtimeToSecond(msg);    
//                }  
            }  
            proc.waitFor();     
            stdout.close();  
            long end = System.currentTimeMillis();
            logger.info("time:"+(end-start));
            return 0;
        } catch (Exception e) {  
            logger.error("执行命令失败",e);
            return 1;
        }  
    }
    public static void main(String[] args) {
        String f = "C:\\eclipse-javaee\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\school-web\\tmp\\t0011486174414661testavi.avi";
        videoConvert(f);
    }
}
