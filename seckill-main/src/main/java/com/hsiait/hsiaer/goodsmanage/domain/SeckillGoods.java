package com.hsiait.hsiaer.goodsmanage.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:54
 **/
@Data
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Double seckillPrice;
    private Date startTime;
    private Date endTime;
}
