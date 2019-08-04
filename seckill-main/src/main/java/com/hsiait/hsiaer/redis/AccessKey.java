package com.hsiait.hsiaer.redis;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 13:22
 **/
public class AccessKey extends BasePrefix {
    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
