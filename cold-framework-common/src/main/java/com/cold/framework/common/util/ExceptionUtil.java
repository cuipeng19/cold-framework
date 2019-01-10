package com.cold.framework.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Tool classes for exception handing.
 *
 * @author cuipeng
 * @date 2019/1/9 14:24
 */
public class ExceptionUtil {

    /**
     * Get exception stack information.
     *
     * @param e exception
     * @return stack information
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
