package com.hsiait.hsiaer.usermanage.controller;

import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.result.Result;
import com.hsiait.hsiaer.usermanage.service.SeckillUserService;
import com.hsiait.hsiaer.usermanage.service.impl.SeckillUserServiceImpl;
import com.hsiait.hsiaer.usermanage.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:05
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SeckillUserService seckillUserService;


    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        String token = seckillUserService.login(response, loginVo);
        return Result.success(token);
    }
}
