package com.hsiait.hsiaer.goodsmanage.service.impl;

import com.hsiait.hsiaer.goodsmanage.domain.SeckillGoods;
import com.hsiait.hsiaer.goodsmanage.mapper.GoodsMapper;
import com.hsiait.hsiaer.goodsmanage.service.GoodsService;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:52
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(GoodsVo goods) {

        SeckillGoods skg = new SeckillGoods();
        skg.setGoodsId(goods.getId());
        int ret = goodsMapper.reduceStock(skg);
        return ret > 0;
    }

    @Override
    public void resetStock(List<GoodsVo> goodsList) {

        for(GoodsVo goods : goodsList){
            SeckillGoods skg = new SeckillGoods();
            skg.setGoodsId(goods.getId());
            skg.setStockCount(goods.getStockCount());
            goodsMapper.resetStock(skg);
        }

    }
}
