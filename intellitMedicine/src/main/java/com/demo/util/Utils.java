package com.demo.util;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.UUID;

/**
 * 常用工具类
 */
public class Utils {
    public static void fileUpload(MultipartHttpServletRequest files){

    }

    /**
     * 为上传文件获取一个UUID的新文件名
     * @param fileName
     * @return
     */
    public static String getNewFileName(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf("."));  //获取文件扩展名
        fileName = UUID.randomUUID().toString().replace("-","") + suffix;   //新的文件名
        return  fileName;
    }
}
