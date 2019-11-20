package com.pxz.pxznetwork;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 类说明：log拦截器
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/11/12 14:26
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();
    private String getString = "--> GET";
    private String postString = "--> POST";
    private String endHttp = "<-- END HTTP";
    private String logTag = "";
    private String type1="{";
    private String type2="}";
    private String type3="[";
    private String type4="]";

    public HttpLogger(String logTag) {
        this.logTag = logTag;
    }

    public HttpLogger() {
    }

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith(postString)) {
            mMessage.setLength(0);
        }
        if (message.startsWith(getString)) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith(type1) && message.endsWith(type2)) || (message.startsWith(type3) && message.endsWith(type4))) {
            message = JsonUtil.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith(endHttp)) {
            LogUtil.d(logTag, JsonUtil.unicodeToUTF_8(mMessage.toString()));
        }
    }
}