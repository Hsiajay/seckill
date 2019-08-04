package com.hsiait.hsiaer.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 12:15
 **/
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {


}
