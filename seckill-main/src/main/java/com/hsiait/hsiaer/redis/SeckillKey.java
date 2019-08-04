package com.hsiait.hsiaer.redis;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 12:42
 **/
public class SeckillKey extends BasePrefix {


    /*public SeckillKey(String prefix) {
        super(prefix);
    }*/

    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey(0, "go");
    public static SeckillKey getMiaoshaPath = new SeckillKey(60, "mp");
    public static SeckillKey getMiaoshaVerifyCode = new SeckillKey(300, "vc");
}
