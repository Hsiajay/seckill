package com.hsiait.hsiaer.usermanage.controller;

import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.result.Result;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import com.hsiait.hsiaer.usermanage.service.impl.SeckillUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: SeckillUser控制类
 * @Author: xiajie
 * @Date: 2019/8/4 16:43
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SeckillUserServiceImpl seckillUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> info(Model model,SeckillUser skuser){

        return Result.success(skuser);

    }

}
