package com.hsiait.hsiaer.goodsmanage.controller;

import com.hsiait.hsiaer.goodsmanage.service.impl.GoodsServiceImpl;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsDetailVo;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import com.hsiait.hsiaer.redis.GoodsKey;
import com.hsiait.hsiaer.redis.RedisService;
import com.hsiait.hsiaer.result.Result;
import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import com.hsiait.hsiaer.usermanage.service.impl.SeckillUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 18:27
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    SeckillUserServiceImpl seckillUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/to_detail2/{goodsId}",produces = "text/html")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, SeckillUser skuser,
                          @PathVariable("goodsId") long goodsId){

        model.addAttribute("user", skuser);
        //取缓存
        /*String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }*/

        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //    	 return "goods_list";
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        //手动渲染
        String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;


    }

    @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model,SeckillUser user,
                          @PathVariable("goodsId")long goodsId) {

        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);

        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        //手动渲染
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if(now < startAt){//秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int)((startAt-now)/1000);

        }else if(now > endAt){//秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;

        }else {//秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("miaoshaStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        //return "goods_detail";

        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
        }
        return html;

    }

    @RequestMapping(value="/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model,SeckillUser skuser,
                                        @PathVariable("goodsId")long goodsId) {
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int seckillStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setSkuser(skuser);
        vo.setRemainSeconds(remainSeconds);
        vo.setSeckillStatus(seckillStatus);
        return Result.success(vo);
    }

}