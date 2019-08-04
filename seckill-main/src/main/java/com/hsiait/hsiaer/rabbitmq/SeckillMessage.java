package com.hsiait.hsiaer.rabbitmq;

import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import lombok.Data;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 19:50
 **/
@Data
public class SeckillMessage {
    private SeckillUser user;
    private long goodsId;
}
