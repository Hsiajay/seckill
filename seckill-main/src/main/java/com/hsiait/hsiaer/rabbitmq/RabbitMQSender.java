package com.hsiait.hsiaer.rabbitmq;

import com.hsiait.hsiaer.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 19:59
 **/
@Service
public class RabbitMQSender {

    private static Logger log = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate ;

    public void sendSeckillMessage(SeckillMessage mm) {
        String msg = RedisService.beanToString(mm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(RabbitMQConfig.SECKILL_QUEUE, msg);
    }

    /*public void send(Object message) {
    	String msg = RedisService.beanToString(message);
    	log.info("send message:"+msg);
    	amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, msg);
    }
    //
    public void sendTopic(Object message) {
    	String msg = RedisService.beanToString(message);
    	log.info("send topic message:"+msg);
    	amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.key1", msg+"1");
    	amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.key2", msg+"2");
    }
    //
    public void sendFanout(Object message) {
    	String msg = RedisService.beanToString(message);
    	log.info("send fanout message:"+msg);
    	amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", msg);
    }
    //
    public void sendHeader(Object message) {
    	String msg = RedisService.beanToString(message);
    	log.info("send fanout message:"+msg);
    	MessageProperties properties = new MessageProperties();
    	properties.setHeader("header1", "value1");
    	properties.setHeader("header2", "value2");
    	Message obj = new Message(msg.getBytes(), properties);
    	amqpTemplate.convertAndSend(RabbitMQConfig.HEADERS_EXCHANGE, "", obj);
    }
*/

}
