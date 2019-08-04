package com.hsiait.hsiaer.usermanage.service.impl;

import com.hsiait.hsiaer.usermanage.domain.User;
import com.hsiait.hsiaer.usermanage.mapper.UserMapper;
import com.hsiait.hsiaer.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 15:14
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }

    @Transactional
    @Override
    public boolean tx() {
        User us1 = new User();
        us1.setId(2);
        us1.setName("22222");
        userMapper.insert(us1);

        User us2 = new User();
        us2.setId(1);
        us2.setName("11111");
        userMapper.insert(us2);
        return true;
    }
}
