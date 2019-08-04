package com.hsiait.hsiaer.exception;

import com.hsiait.hsiaer.result.CodeMsg;

/**
 * @Description: 全局异常类
 * @Author: xiajie
 * @Date: 2019/8/3 18:36
 **/
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
