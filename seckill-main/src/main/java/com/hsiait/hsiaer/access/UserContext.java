package com.hsiait.hsiaer.access;

import com.hsiait.hsiaer.usermanage.domain.SeckillUser;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 19:39
 **/
public class UserContext {
    private static ThreadLocal<SeckillUser> userHolder = new ThreadLocal<SeckillUser>();

    public static void setUser(SeckillUser user) {
        userHolder.set(user);
    }

    public static SeckillUser getUser() {
        return userHolder.get();
    }
}
