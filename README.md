# 介绍
## 自用项目，自定义标题栏

### 图片
<div style="align: center">
       <img src="https://github.com/yiranchunqiu/TitleBarView/blob/master/pic/%E5%9B%BE%E7%89%871.png" width="32%">
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
    	        implementation 'com.github.yiranchunqiu:TitleBarView:1.0'
    	}
```

### 使用

```
<com.pxz.pxztitlebar.TitleBarView
                android:id="@+id/titleBarView8"
                android:layout_width="match_parent"
                android:layout_height="44dp"
                android:layout_marginTop="20dp"
                app:titleBarColor="@color/colorPrimary"
                app:leftOnePic="@mipmap/title_back_black"
                app:isLeftTwoPic="true"
                app:leftTwoPic="@mipmap/title_back_black"
                app:isRightOneText="true"
                app:rightOneText="副标题1"
                app:isRightTwoText="true"
                app:rightTwoText="副标题2"
                app:titleBarText="标题7" />
```