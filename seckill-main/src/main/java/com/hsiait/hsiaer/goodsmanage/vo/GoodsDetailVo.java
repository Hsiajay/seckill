package com.hsiait.hsiaer.goodsmanage.vo;

import com.hsiait.hsiaer.usermanage.domain.SeckillUser;
import lombok.Data;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 17:01
 **/
@Data
public class GoodsDetailVo {
    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private SeckillUser skuser;
}
