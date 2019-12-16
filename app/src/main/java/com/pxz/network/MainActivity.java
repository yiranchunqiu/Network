package com.pxz.network;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明：首页
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @time 2019/11/20 13:00
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvPost;
    private TextView tvGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOnClick();
    }

    private void initView() {
        tvPost = findViewById(R.id.tv_post);
        tvGet = findViewById(R.id.tv_get);
    }

    Disposable subscribe1;
    Disposable subscribe2;

    private void initOnClick() {
        Bean bean = new Bean("yiranchunqiu", "123456");
        tvPost.setOnClickListener(v -> {
            subscribe1 = App.getInstance().httpUtil1.createService(AppApi.class)
                    .login(bean)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            o -> Log.d("测试1", o.toString()),
                            throwable -> Log.d("测试2", throwable.toString())
                    );
        });
        tvGet.setOnClickListener(v -> {
            subscribe2 = App.getInstance().httpUtil2.createService(AppApi.class)
                    .weatherXiaomi("0", "0", "weathercn:101220101", "1",
                            "weather20151024", "zUFJoAR2ZVrDy1vF3D07", "false", "zh_cn")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            o -> Log.d("测试1", o.toString()),
                            throwable -> Log.d("测试2", throwable.toString())
                    );
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe1!=null&&subscribe1.isDisposed()){
            subscribe1.dispose();
            subscribe1=null;
        }
        if (subscribe2!=null&&subscribe2.isDisposed()){
            subscribe2.dispose();
            subscribe2=null;
        }
    }
}