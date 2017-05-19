/*******************************************************************************
 * Project: school-common
 * Package: com.yckj.school.common.util
 * Type:    DecomprossingUtils
 * Author:  hefengwen
 * Date:    2017-01-04 17:38:29
 *
 * Copyright (c) 2017 AFCATSTAR. All Rights Reserved.
 *******************************************************************************/
package com.yckj.school.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hefengwen
 */
public class DecomprossUtils {

    private static final Logger logger = LoggerFactory.getLogger(DecomprossUtils.class);
    
    private static final int BUFFER = 2048; 

    public static void main(String[] args) throws ZipException, IOException {
        String srcZip = "G:/jquery-mousewheel-master.zip";
        String target = "G:/test/";
        zipDecomprossing(srcZip, target);
    }
    @SuppressWarnings("unchecked")
    public static void zipDecomprossing(String srcZip, String target) throws IOException {
        long startTime = System.currentTimeMillis();
        try {
            File pathFile = new File(target);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(new File(srcZip));
            for (Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries(); entries.hasMoreElements();) {
                ZipEntry entry = entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (target + zipEntryName).replaceAll("\\*", "/");
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[BUFFER];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            zip.close();
        }
        catch (IOException e) {
            logger.error("",e);
            throw e;
        }
        long endTime = System.currentTimeMillis();
        logger.info("耗费时间：" + (endTime - startTime) + "ms");
    }
}
