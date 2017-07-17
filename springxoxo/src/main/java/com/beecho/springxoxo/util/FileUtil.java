package com.beecho.springxoxo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author 春哥大魔王
 */

public class FileUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取文件路径
     * @param filePath 文件路径
     * @return 加上当前时间的目录路径
     */
    public static String getPath(String filePath) {
        SimpleDateFormat sdfForDir = new SimpleDateFormat("yyyyMMdd");
        Calendar date = Calendar.getInstance();
        String dateFormat = sdfForDir.format(date.getTime());
        return new StringBuilder(filePath).append("/").append(dateFormat).append("/").toString();
    }

    /**
     * 创建磁盘目录
     * @param filePath 文件根路径
     * @return 加上当前时间目录的路径
     */
    public static String createPath(String filePath) {
        SimpleDateFormat sdfForDir = new SimpleDateFormat("yyyyMMdd");
        Calendar date = Calendar.getInstance();
        String dateFormat = sdfForDir.format(date.getTime());
        StringBuffer path = new StringBuffer(filePath).append("/").append(dateFormat);

        File f = new File(path.toString());
        if (f.exists()) {
            String[] children = f.list();
            for (String c : children) {
                new File(f, c).delete();
            }
        } else {
            f.mkdir();
        }
        return path.append("/").toString();
    }

    /**
     * 将Json数据写入磁盘，分批写释放内存
     * @param path 文件全路径
     * @param content 写入内容
     */
    public static void writeToFile(String path,String content) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
            // 文件长度，字节数
            long fileLength = randomAccessFile.length();
            // 将写文件指针移到文件尾
            randomAccessFile.seek(fileLength);
            randomAccessFile.write(content.getBytes("UTF8"));
        } catch (IOException e) {
            LOGGER.error("写入全量专辑文件失败", e);
        }
    }
}
