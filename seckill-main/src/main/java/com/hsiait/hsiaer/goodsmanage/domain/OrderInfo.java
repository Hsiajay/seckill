package com.hsiait.hsiaer.goodsmanage.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 16:56
 **/
@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
