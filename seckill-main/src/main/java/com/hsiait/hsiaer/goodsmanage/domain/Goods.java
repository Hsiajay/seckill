package com.hsiait.hsiaer.goodsmanage.domain;

import lombok.Data;

/**
 * @Description: 商品类
 * @Author: xiajie
 * @Date: 2019/8/4 16:53
 **/
@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
