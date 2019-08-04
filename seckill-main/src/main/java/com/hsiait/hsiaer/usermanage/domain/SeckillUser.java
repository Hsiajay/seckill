package com.hsiait.hsiaer.usermanage.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 秒杀用户
 * @Author: xiajie
 * @Date: 2019/8/4 13:49
 **/
@Data
public class SeckillUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

}
