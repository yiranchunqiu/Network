package com.pxz.network;

import android.app.Application;

import com.pxz.pxznetwork.HttpUtil;

/**
 * 类说明：
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/11/20 14:26
 */
public class App extends Application {
    public static App app;
    public HttpUtil httpUtil1;
    public HttpUtil httpUtil2;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化基类
        app = this;
        httpUtil1=new HttpUtil("https://www.wanandroid.com");
        httpUtil2=new HttpUtil("https://weatherapi.market.xiaomi.com");
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    public static synchronized App getInstance() {
        if (app == null) {
            synchronized (App.class) {
                if (app == null) {
                    app = new App();
                }
            }
        }
        return app;
    }
}