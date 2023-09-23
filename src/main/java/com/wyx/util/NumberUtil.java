package com.wyx.util;

import java.util.Random;

public class NumberUtil {
    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static String ALPHABET_NUMBER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static Random random = new Random();
    public static String getRandomNumber(){
        Integer num = 13;
        //获取随机的email前缀,自定义前缀长度
        String firstPart = "";
        char[] c = ALPHABET_NUMBER.toCharArray();
        for (int i = 0; i < num; i++){
            firstPart += c[random.nextInt(c.length)];
        }
        return firstPart;
    }
}
