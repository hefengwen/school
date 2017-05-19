/*******************************************************************************
 * Project: school-common
 * Package: com.yckj.school.common.util
 * Type:    PdfConvertUtils
 * Author:  hefengwen
 * Date:    2017-02-04 12:37:21
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将PDF文档转换为swf格式的FLASH文件. 运行该函数需要用到SWFTools, 下载地址为
 * http://www.swftools.org/download.html
 * 
 * <pre>
 *  
 * 示例: 
 * String sourcePath = "F:\\PDF\\source.pdf"; 
 * String destFile = "F:\\SWF\\dest.swf"; 
 * try { 
 *  Converter.pdf2SWF(sourcePath, destFile); 
 * } catch (IOException e) { 
 *  e.printStackTrace(); 
 * }
 * </pre>
 * 
 * @param sourceFile
 *            源文件(即PDF文档)路径, 包括源文件的文件名. 示例: D:\\PDF\\source.pdf
 * @param destFile
 *            目标文件路径, 即需要保存的文件路径(包括文件名). 示例: D:\\SWF\\dest.swf
 * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源PDF文件, 或配置文件url.properties配置错误; 如果返回 0,
 *         则表示操作成功; 返回1或其他, 则表示转换失败
 * @author hefengwen
 */
public class PdfConvertUtils {
    private static final Logger logger = LoggerFactory.getLogger(PdfConvertUtils.class);
    
    private static final String SWF_WIN = "E:\\SWFTools\\pdf2swf.exe";
    private static final String SWF_LINUX = "/usr/local/swftools/bin/pdf2swf";
    
    public static String pdf2SWF(String oldFilePath) {

        if (!checkfile(oldFilePath)) {  
            logger.info(oldFilePath + " is not file");  
            return null;  
        }  
        logger.info("当前转换文件:"+oldFilePath);
        String newFilePath = oldFilePath.substring(0,oldFilePath.lastIndexOf(".") + 1)+"swf";
        try {
            // 调用pdf2swf命令进行转换swfextract -i - sourceFilePath.pdf -o destFilePath.swf
            String command = null;
            String os = System.getProperty("os.name"); 
            if(os.startsWith("Win")){
                command = SWF_WIN + "  -i " + oldFilePath + " -o " + newFilePath;
            }else{
                command = SWF_LINUX + "  -i " + oldFilePath + " -o " + newFilePath;
            }
            Process proc = Runtime.getRuntime().exec(command);
            long start = System.currentTimeMillis();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line;
            while ((bufferedReader.readLine()) != null) {
//                logger.info(line);
            }
            proc.waitFor();
            bufferedReader.close();
            long end = System.currentTimeMillis();
            logger.info("time:"+(end-start));
            return newFilePath;
        }
        catch (InterruptedException e) {
            logger.error("",e);
        }
        catch (IOException e1) {
            logger.error("",e1);
        }

        return null;
    }
    private static boolean checkfile(String path) {  
        File file = new File(path);  
        if (!file.isFile()) {  
            return false;  
        }  
        return true;  
    }  
    public static void main(String[] args) {
        String f = "F:\\MyBatis3.pdf";
        pdf2SWF(f);
    }
}
