package com.hsiait.hsiaer.redis;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 18:04
 **/
public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("moug");
}
