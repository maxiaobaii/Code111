package com.baizhi.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

/**
 * @ClassNmae: SaltUtil
 * @Author: yddm
 * @DateTime: 2020/9/15 22:04
 * @Description: TODO
 */

public class SaltUtil {
    public static String getSalt(Integer n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!~@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer = 0; integer < n; integer++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(4);
        Md5Hash md5Hash = new Md5Hash("123456", salt, 1024);
        String s = md5Hash.toHex();
        System.out.println("s = " + s + "\t" + salt);
    }
}
