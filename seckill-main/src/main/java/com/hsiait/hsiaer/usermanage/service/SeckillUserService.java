package com.hsiait.hsiaer.usermanage.service;

import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import com.hsiait.hsiaer.usermanage.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 15:22
 **/
public interface SeckillUserService {
    public SeckillUser getById(long id);
    public boolean updatePassword(String token, long id, String formPass);
    public SeckillUser getByToken(HttpServletResponse response, String token);
    public String login(HttpServletResponse response, LoginVo loginVo);

    //public void addCookie(HttpServletResponse response, String token, SeckillUser user);

}
