package com.evax.common.utils;


import org.springframework.util.DigestUtils;

/**
 * @author Lionel
 */
public class Md5Util {

    private static final String SECRET = "TwKk26ZHAxcWu3UP378sUQqorlUpw8Ja";
    private static final String ALGORITHM_NAME = "md5";
    private static final int HASH_ITERATIONS = 2;

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String str) {
        return md5(str, SECRET);
    }

    public static String md5(String str, String salt) {
        String base = str + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 使用md5的算法进行加密
     */
    public static boolean verify(String md5, String str, String salt) {
        String md5code = md5(str, salt);
        return md5code.equals(md5);
    }

    public static void main(String[] args) {
        String password = "123456";
        String md5 = md5(password);
        System.err.println("md5>>" + md5);
    }
}