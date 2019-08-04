package com.hsiait.hsiaer.usermanage.service;

import com.hsiait.hsiaer.usermanage.domain.User;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 15:13
 **/
public interface UserService {
    public User getById(int id);
    public boolean tx();
}
