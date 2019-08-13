package com.hsiait.hsiaer.rabbitmq;

import com.hsiait.hsiaer.goodsmanage.domain.SeckillOrder;
import com.hsiait.hsiaer.goodsmanage.service.impl.GoodsServiceImpl;
import com.hsiait.hsiaer.goodsmanage.service.impl.OrderServiceImpl;
import com.hsiait.hsiaer.goodsmanage.service.impl.SeckillServiceImpl;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 19:55
 **/
@Service
public class RabbitMQReceiver {

    private static Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    SeckillServiceImpl seckillService;

    @RabbitListener(queues=RabbitMQConfig.SECKILL_QUEUE)
    public void receive(String message) {
        log.info("receive message:"+message);
        SeckillMessage skmsg  = RedisService.stringToBean(message, SeckillMessage.class);
        SeckillUser user = skmsg.getUser();
        long goodsId = skmsg.getGoodsId();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        seckillService.seckill(user, goods);
    }

    /*@RabbitListener(queues=RabbitMQConfig.QUEUE)
    public void receive(String message) {
        log.info("receive message:"+message);
    }

    @RabbitListener(queues=RabbitMQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic  queue1 message:"+message);
    }

    @RabbitListener(queues=RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info(" topic  queue2 message:"+message);
    }

    @RabbitListener(queues=RabbitMQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        log.info(" header  queue message:"+new String(message));
    }*/


}
