package com.hsiait.hsiaer.goodsmanage.service.impl;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import com.hsiait.hsiaer.goodsmanage.domain.SeckillOrder;
import com.hsiait.hsiaer.goodsmanage.mapper.OrderMapper;
import com.hsiait.hsiaer.goodsmanage.service.OrderService;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.redis.OrderKey;
import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 18:00
 **/
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RedisService redisService;

    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        //return orderDao.getSeckillOrderByUserIdGoodsId(userId, goodsId);
        return redisService.get(OrderKey.getSeckillOrderByUidGid, ""+userId+"_"+goodsId, SeckillOrder.class);
    }

    @Override
    public OrderInfo getOrderById(long orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Transactional
    @Override
    public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderMapper.insert(orderInfo);

        SeckillOrder skorder = new SeckillOrder();
        skorder.setGoodsId(goods.getId());
        skorder.setOrderId(orderInfo.getId());
        skorder.setUserId(user.getId());
        orderMapper.insertSeckillOrder(skorder);

        redisService.set(OrderKey.getSeckillOrderByUidGid, ""+user.getId()+"_"+goods.getId(), skorder);
        return orderInfo;
    }

    @Override
    public void deleteOrders() {
        orderMapper.deleteOrders();
        orderMapper.deleteSeckillOrders();
    }
}
