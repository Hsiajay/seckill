package com.hsiait.hsiaer.usermanage.mapper;

import com.hsiait.hsiaer.usermanage.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 14:34
 **/
public interface UserMapper {

    public User getById(@Param("id")int id);

    public int insert(User user);
}
