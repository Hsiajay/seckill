package com.hsiait.hsiaer.redis;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 12:55
 **/
public class SeckillUserKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600*24 * 2;

    public SeckillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE,"tk");
    public static SeckillUserKey getById = new SeckillUserKey(0,"id");
}
