package com.beecho.springxoxo.business;

import com.beecho.springxoxo.service.DataService;
import com.beecho.springxoxo.startup.Startup;
import com.beecho.springxoxo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.NumberUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * @author 春哥大魔王
 */

public class FullStartupTask extends TimerTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(FullStartupTask.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private String fullPath="/data/epiphany";
    private DataService dataService=null;
    private int dealOneTime = 200;
    private String redisNoKey = "epiphanyNo";

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void setDealOneTime(int dealOneTime) {
        this.dealOneTime = dealOneTime;
    }

    public void setRedisNoKey(String redisNoKey) {
        this.redisNoKey = redisNoKey;
    }

    @Override
    public void run() {
        redisTemplate.opsForValue().set(redisNoKey, "0");
        clearData();
        FileUtil.createPath(fullPath);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String maxKey = new StringBuilder("max").append(redisNoKey).toString();
        String maxId = valueOperations.get(maxKey);
        if(maxId==null) {
            maxId = String.valueOf(dataService.getMaxId());
            valueOperations.set(maxKey, maxId);
        }
        String minKey = new StringBuilder("min").append(redisNoKey).toString();
        String minId = valueOperations.get(minKey);
        if(minId==null) {
            minId = String.valueOf(dataService.getMinId());
            valueOperations.set(minKey, minId);
        }
        Startup.isRunning=true;
        for(int i=0; i<Startup.FULL_THREAD_TOTAL; i++) {
            FullBusinessTask fullBusinessTask = (FullBusinessTask) Startup.context.getBean("fullBusinessTask");
//            fullBusinessTask.setMinId(NumberUtils.toLong(minId));
//            fullBusinessTask.setMaxId(NumberUtils.toLong(maxId));
            fullBusinessTask.setThreadNo(i);
            fullBusinessTask.setFullPath(fullPath);
            fullBusinessTask.setDataService(dataService);
            fullBusinessTask.setDealOneTime(dealOneTime);
            fullBusinessTask.setRedisNoKey(redisNoKey);
            Startup.service.execute(fullBusinessTask);
        }
    }

    private void clearData() {
        SimpleDateFormat sdfForDir = new SimpleDateFormat("yyyyMMdd");
        Calendar now = Calendar.getInstance();
        File dir = new File(fullPath);
        if (dir.isDirectory()) {
            //递归删除目录中的子目录下
            if(dir.isDirectory()) {
                String[] dateDirs = dir.list();
                for(int j=0; j<dateDirs.length; j++) {
                    if(dateDirs[j]!=null) {
                        Date date = null;
                        try {
                            date = sdfForDir.parse(dateDirs[j]);
                        } catch (ParseException e) {
                            LOGGER.error("日期解析错误", e);
                        }
                        if(now.getTimeInMillis()-date.getTime()>2*24*3600*1000) {
                            deleteDir(new File(new StringBuffer(fullPath).append("/").append(dateDirs[j]).toString()));
                        }
                    }
                }
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        boolean isDeleted = dir.delete();
        LOGGER.info("删除目录"+dir.getName()+(isDeleted?"成功":"失败"));
        return isDeleted;
    }
}