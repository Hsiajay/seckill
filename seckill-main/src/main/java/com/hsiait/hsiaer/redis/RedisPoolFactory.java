package com.hsiait.hsiaer.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 12:59
 **/
@Service
public class RedisPoolFactory {

    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPool jp = new JedisPool();
        return jp;
    }

}
