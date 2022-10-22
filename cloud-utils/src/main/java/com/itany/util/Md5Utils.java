package com.itany.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Md5Utils.
 *
 * @author Thou
 * @date 2022/10/18
 */
public class Md5Utils {

    /**
     * 加密
     *
     * @param s 明文
     * @return java.lang.String
     * @author Thou
     * @date 2022/10/18
     */
    public static String md5(String s) {
        return DigestUtils.md5Hex(s);
    }

    /**
     * 验证
     *
     * @param s 明文
     * @param md5 密文
     * @return boolean
     * @author Thou
     * @date 2022/10/18
     */
    public static boolean verify(String s, String md5) {
        String s1 = md5(s);
        return s1.equalsIgnoreCase(md5);
    }
}
