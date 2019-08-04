package com.hsiait.hsiaer.goodsmanage.service;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import com.hsiait.hsiaer.goodsmanage.domain.SeckillOrder;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:58
 **/
public interface OrderService {

    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId);

    public OrderInfo getOrderById(long orderId);

    public OrderInfo createOrder(SeckillUser user, GoodsVo goods);

    public void deleteOrders();

}
