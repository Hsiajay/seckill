package com.hsiait.hsiaer.goodsmanage.controller;

import com.hsiait.hsiaer.goodsmanage.domain.OrderInfo;
import com.hsiait.hsiaer.goodsmanage.service.impl.GoodsServiceImpl;
import com.hsiait.hsiaer.goodsmanage.service.impl.OrderServiceImpl;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.goodsmanage.vo.OrderDetailVo;
import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.result.CodeMsg;
import com.hsiait.hsiaer.result.Result;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import com.hsiait.hsiaer.usermanage.service.impl.SeckillUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 18:52
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    SeckillUserServiceImpl seckillUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    GoodsServiceImpl goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, SeckillUser user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
