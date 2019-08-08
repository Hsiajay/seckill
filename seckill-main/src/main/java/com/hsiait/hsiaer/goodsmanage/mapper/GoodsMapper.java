package com.hsiait.hsiaer.goodsmanage.mapper;

import com.hsiait.hsiaer.goodsmanage.domain.SeckillGoods;
import com.hsiait.hsiaer.goodsmanage.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:57
 **/
@Repository
public interface GoodsMapper {

    @Select("select g.*,sk.stock_count, sk.start_time, sk.end_time,sk.seckill_price from seckill_goods sk left join goods g on sk.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,sk.stock_count, sk.start_time, sk.end_time,sk.seckill_price from seckill_goods sk left join goods g on sk.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);

    @Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    public int reduceStock(SeckillGoods skg);

    @Update("update seckill_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    public int resetStock(SeckillGoods skg);

}
