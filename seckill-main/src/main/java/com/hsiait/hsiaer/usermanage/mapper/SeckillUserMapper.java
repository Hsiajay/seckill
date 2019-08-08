package com.hsiait.hsiaer.usermanage.mapper;

import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 14:52
 **/
@Repository
public interface SeckillUserMapper {

    public SeckillUser getById(@Param("id")long id);

    public void update(SeckillUser toBeUpdate);
}
