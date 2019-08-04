package com.hsiait.hsiaer.usermanage.vo;

import com.hsiait.hsiaer.validator.IsMobile;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author: xiajie
 * @Date: 2019/8/4 15:32
 **/
@Data
@ToString
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min=32)
    private String password;
}
