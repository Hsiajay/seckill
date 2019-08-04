package com.hsiait.hsiaer.goodsmanage.service;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 19:11
 **/
public interface SeckillService {

    public OrderInfo seckill(SeckillUser user, GoodsVo goods);

    public long getSeckillResult(Long userId, long goodsId);

    public void reset(List<GoodsVo> goodsList);

    public boolean checkPath(SeckillUser user, long goodsId, String path);

    public String createSeckillPath(SeckillUser user, long goodsId);

    public BufferedImage createVerifyCode(SeckillUser user, long goodsId);

    public boolean checkVerifyCode(SeckillUser user, long goodsId, int verifyCode);


}
