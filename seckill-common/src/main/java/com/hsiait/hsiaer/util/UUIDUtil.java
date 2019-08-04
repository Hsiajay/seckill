package com.hsiait.hsiaer.util;

import java.util.UUID;

/**
 * @Description: 通用唯一识别码工具
 * @Author: xiajie
 * @Date: 2019/8/3 15:08
 **/
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
