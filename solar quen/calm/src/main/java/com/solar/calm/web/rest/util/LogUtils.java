package com.solar.calm.web.rest.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {

    public static void trackInfo(Logger logger, String msg) {
        logger.info(generateTraceString(msg, null));
    }

    public static void trackWarn(Logger logger, String msg) {
        logger.warn(generateStackTrace(msg));
    }

    public static void traceDebug(Logger logger, Throwable e) {
        logger.debug(generateTraceString(null, e));
    }

    public static void traceDebug(Logger logger, Throwable e, String errorMsg) {
        logger.debug(generateTraceString(errorMsg, e));
    }

    public static void traceError(Logger logger, String errorMsg) {
        traceError(logger, null, errorMsg);
    }

    public static void traceError(Logger logger, Throwable e) {
        traceError(logger, e, null);
    }

    public static void traceError(Logger logger, Throwable e, String errorMsg) {
        logger.error(generateTraceString(errorMsg, e));
    }

    public static String generateStackTrace(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(msg);
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < trace.length; i++) {
            sb.append("\tat ");
            sb.append(trace[i]).append("\n");
        }
        return sb.toString();
    }

    public static String generateTraceString(String errorMsg, Throwable e) {
        StringWriter w = new StringWriter();
        w.append("Message is: ").append(errorMsg);
        PrintWriter out = new PrintWriter(w);
        if (!StringUtils.isEmpty(errorMsg)) {
            out.println(errorMsg);
        }
        if (e != null) {
            e.printStackTrace(out);
        }
        return w.toString();
    }

}
