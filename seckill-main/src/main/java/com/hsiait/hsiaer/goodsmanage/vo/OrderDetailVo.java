package com.hsiait.hsiaer.goodsmanage.vo;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import lombok.Data;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:03
 **/
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
