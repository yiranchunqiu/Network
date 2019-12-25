package com.pxz.pxznetwork;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;

/**
 * 类说明：log日志
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @time 2019/6/5 13:26
 */
public class LogUtil {
    /**
     * debug开关
     */
    public static boolean D = true;
    public final static String LOG_D = "LOG_D";
    /**
     * info开关
     */
    public static boolean I = true;
    public final static String LOG_I = "LOG_I";
    /**
     * error开关
     */
    public static boolean E = true;
    public final static String LOG_E = "LOG_E";
    /**
     * 起始执行时间
     */
    public static long startLogTimeInMillis = 0;

    /**
     * debug日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void d(String tag, String message) {
        if (D) {
            show(LOG_D, tag, message);
        }
    }

    /**
     * debug日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void d(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        d(tag, message);
    }

    /**
     * debug日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void d(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        d(tag, message);
    }

    /**
     * info日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void i(String tag, String message) {
        if (I) {
            show(LOG_I, tag, message);
        }
    }

    /**
     * info日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void i(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        i(tag, message);
    }

    /**
     * info日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void i(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        i(tag, message);
    }

    /**
     * error日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void e(String tag, String message) {
        if (E) {
            show(LOG_E, tag, message);
        }
    }

    /**
     * error日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void e(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        e(tag, message);
    }

    /**
     * error日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void e(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        e(tag, message);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param tag 描述
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        d(tag, "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param context 上下文
     */
    public static void prepareLog(Context context) {
        String tag = context.getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param clazz 类
     */
    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + "ms");
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Context context, String message, boolean printTime) {
        String tag = context.getClass().getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param clazz     标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Class<?> clazz, String message, boolean printTime) {
        String tag = clazz.getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * log长度过长的处理
     *
     * @param type 日志等级
     * @param tag  标志
     * @param msg  日志
     */
    private static void show(String type, String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }
        msg = msg.trim();
        int index = 0;
        int segmentSize = 3 * 1024;
        String logContent;
        while (index < msg.length()) {
            if (msg.length() <= index + segmentSize) {
                logContent = msg.substring(index);
            } else {
                logContent = msg.substring(index, segmentSize + index);
            }
            index += segmentSize;
            switch (type) {
                case LOG_D:
                    Log.d(tag, logContent.trim());
                    break;
                case LOG_I:
                    Log.i(tag, logContent.trim());
                    break;
                case LOG_E:
                    Log.e(tag, logContent.trim());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * debug日志的开关
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     */
    public static void openAll() {
        D = true;
        I = true;
        E = true;
    }

    /**
     * 关闭所有日志
     */
    public static void closeAll() {
        D = false;
        I = false;
        E = false;
    }
}