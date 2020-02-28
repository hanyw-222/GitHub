package com.demo.dao;

import org.apache.ibatis.annotations.Param;

public interface IPowerDao {
    /**
     * 权限是否存在
     * @param groupid
     * @param url
     * @return
     */
    int isPowerExist(@Param("groupid") int groupid, @Param("url") String url);
}
