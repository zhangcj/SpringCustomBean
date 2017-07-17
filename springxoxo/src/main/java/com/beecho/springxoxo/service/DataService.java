package com.beecho.springxoxo.service;

import java.util.List;

/**
 * @author 春哥大魔王
 */

public interface DataService {

    /**
     * 根据ID进行业务数据处理
     * @param dealIds
     * @param path
     */
    public void dealDataByIds(List<Long> dealIds,String path);

    /**
     * 根据时间区间获取ID列表
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<Long> getIds(String beginTime,String endTime);

    /**
     * 根据开始结束ID处理数据
     * @param beginId
     * @param endId
     * @param path
     */
    public void dealDataByBeginEnd(long beginId,long endId,String path);

    /**
     * 获取最大ID
     * @return
     */
    public long getMaxId();

    /**
     * 获取最小ID
     * @return
     */
    public long getMinId();
}
