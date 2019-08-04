package com.hsiait.hsiaer.goodsmanage.domain;

import lombok.Data;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:55
 **/
@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
