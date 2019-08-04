package com.hsiait.hsiaer.redis;

/**
 * @Description: key前缀接口
 * @Author: xiajie
 * @Date: 2019/8/4 12:26
 **/
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
