package com.hsiait.hsiaer.goodsmanage.service;

import com.hsiait.hsiaer.goodsmanage.mapper.GoodsMapper;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:49
 **/
public interface GoodsService {

    public List<GoodsVo> listGoodsVo();

    public GoodsVo getGoodsVoByGoodsId(long goodsId);

    public boolean reduceStock(GoodsVo goods);

    public void resetStock(List<GoodsVo> goodsList);
}
