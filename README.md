# 介绍
## 自用项目，基于rxjava2.x+okhttp3.x+retrofit2.x封装的网络工具类

### 图片
<div style="align: center">
       <img src="https://github.com/yiranchunqiu/Network/blob/master/pic/%E5%9B%BE%E7%89%871.png" width="32%">
</div>


### 使用方法
### 添加

```
allprojects {
 		repositories {
 			maven { url 'https://jitpack.io' }
 		}
 	}
```

### 添加依赖

```
 	dependencies {
    	        implementation 'com.github.yiranchunqiu:Network:1.0'
    	}
```

### 使用

```
 subscribe2 = App.getInstance().httpUtil2.createService(AppApi.class)
                    .weatherXiaomi("0", "0", "weathercn:101220101", "1",
                            "weather20151024", "zUFJoAR2ZVrDy1vF3D07", "false", "zh_cn")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            o -> Log.d("测试", o.toString()),
                            throwable -> Log.d("测试", throwable.toString())
                    );
```
```
 subscribe1 = App.getInstance().httpUtil1.createService(AppApi.class)
                                        .login(bean)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                o -> Log.d("测试", o.toString()),
                                                throwable -> Log.d("测试", throwable.toString())
                                        );
```