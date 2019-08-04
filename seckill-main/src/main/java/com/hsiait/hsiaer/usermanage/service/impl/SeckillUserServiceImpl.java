package com.hsiait.hsiaer.usermanage.service.impl;

import com.hsiait.hsiaer.exception.GlobalException;
import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.redis.SeckillUserKey;
import com.hsiait.hsiaer.result.CodeMsg;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import com.hsiait.hsiaer.usermanage.mapper.SeckillUserMapper;
import com.hsiait.hsiaer.usermanage.service.SeckillUserService;
import com.hsiait.hsiaer.usermanage.vo.LoginVo;
import com.hsiait.hsiaer.util.MD5Util;
import com.hsiait.hsiaer.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 15:39
 **/
@Service
public class SeckillUserServiceImpl implements SeckillUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    SeckillUserMapper seckillUserMapper;

    @Autowired
    RedisService redisService;

    @Override
    public SeckillUser getById(long id) {

        //取缓存
        SeckillUser skuser = redisService.get(SeckillUserKey.getById,""+id,SeckillUser.class);
        if(skuser != null){
            return skuser;
        }
        //取数据库
        skuser = seckillUserMapper.getById(id);
        if(skuser != null){
            redisService.set(SeckillUserKey.getById,""+id,skuser);
        }
        return skuser;
    }

    @Override
    public boolean updatePassword(String token, long id, String formPass) {
        //取seckillUser
        SeckillUser skus = getById(id);

        if(skus == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        //更新数据库
        SeckillUser skusupdate = new SeckillUser();
        skusupdate.setId(id);
        skusupdate.setPassword(MD5Util.formPassToDBPass(formPass,skus.getSalt()));
        seckillUserMapper.update(skusupdate);

        //处理缓存
        redisService.delete(SeckillUserKey.getById,""+id);
        skus.setPassword(skusupdate.getPassword());
        redisService.set(SeckillUserKey.token,token,skus);
        return true;
    }

    @Override
    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }

        SeckillUser skus = redisService.get(SeckillUserKey.token,token,SeckillUser.class);

        //延长有效期
        if(skus != null){
            addCookie(response,token,skus);
        }
        return skus;
    }

    @Override
    public String login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SeckillUser skus = getById(Long.parseLong(mobile));
        if(skus != null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = skus.getPassword();
        String saltDB = skus.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, skus);

        return token;
    }

    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SeckillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);

    }
}
