package com.hsiait.hsiaer.result;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Description: 公用结果信息类
 * @Author: xiajie
 * @Date: 2019/8/3 14:24
 **/
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;


    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        if(codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    /**
     * @Description //成功的时候调用
     * @Author
     * @Date 2019/8/3 14:40
     * @Param [data]
     * @Return com.hsiait.hsiaer.result.Result<T>
     **/
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * @Description //失败的时候调用
     * @Author
     * @Date 2019/8/3 14:41
     * @Param [codeMsg]
     * @Return com.hsiait.hsiaer.result.Result<T>
     **/
    public static  <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

}
