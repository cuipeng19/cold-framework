package com.cold.framework.common.util;

import java.util.Random;

/**
 * Tool classes for strings.
 *
 * @author cuipeng
 * @date 2019/1/17 22:07
 */
public class StringUtil {

    /**
     * Generate random numbers and convert them to strings.
     *
     * @param n strings length
     * @return
     */
    public static String randomStr(int n) {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<n; i++) {
            buffer.append(new Random().nextInt(10));
        }
        return buffer.toString();
    }
}
