package com.xsungroup.tms.utils;

/**
 * 数字处理
 */
public class NumUtil {


    /**
     * 生成六位随时数
     * @return
     */
    public static int random(){
        return (int)((Math.random()*9+1)*100000);
    }
}
