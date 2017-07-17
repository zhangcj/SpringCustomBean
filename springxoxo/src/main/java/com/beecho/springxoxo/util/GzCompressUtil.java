package com.beecho.springxoxo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.Deflater;

/**
 * @author 春哥大魔王
 */

public class GzCompressUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzCompressUtil.class);
    private static final ExecutorService fixThreadPoolForGz = Executors.newFixedThreadPool(6);

    /**
     * 压缩
     * @param data 待压缩数据
     * @return 压缩后数据
     */
    public static byte[] compress(byte[] data) {
        byte[] output = new byte[0];

        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
            byte[] buf = new byte[data.length];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            LOGGER.error("队列消息压缩错误", e);
        }
        compresser.end();
        return output;
    }
}
