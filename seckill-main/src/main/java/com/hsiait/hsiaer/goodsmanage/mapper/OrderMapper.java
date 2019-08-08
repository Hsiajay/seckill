package com.hsiait.hsiaer.goodsmanage.mapper;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import com.hsiait.hsiaer.goodsmanage.domain.SeckillGoods;
import com.hsiait.hsiaer.goodsmanage.domain.SeckillOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:04
 **/
@Repository
public interface OrderMapper {

    public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    public int insertSeckillOrder(SeckillOrder seckillOrder);

    public OrderInfo getOrderById(@Param("orderId")long orderId);

    public void deleteOrders();

    public void deleteSeckillOrders();



}
