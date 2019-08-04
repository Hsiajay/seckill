package com.hsiait.hsiaer.goodsmanage.vo;

import com.hsiait.hsiaer.goodsmanage.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:58
 **/
@Data
public class GoodsVo extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
