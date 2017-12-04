package com.ly.test;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{
    private static final String TAG = "fuck";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testBuild(View v)
    {
        Lg.d("主板：" + Build.BOARD);
        Lg.d("Android系统定制商：" + Build.BRAND);
        Lg.d("CPU指令集：" + Build.SUPPORTED_ABIS);
        Lg.d("设备参数：" + Build.DEVICE);
        Lg.d("显示屏参数：" + Build.DISPLAY);
        Lg.d("唯一编号：" + Build.FINGERPRINT);
        Lg.d("硬件序列号：" + Build.SERIAL);
        Lg.d("修订版本列表：" + Build.ID);
        Lg.d("硬件制造商：" + Build.MANUFACTURER);
        Lg.d("版本：" + Build.MODEL);
        Lg.d("硬件名：" + Build.HARDWARE);
        Lg.d("手机产品名：" + Build.PRODUCT);
        Lg.d("描述Build的标签：" + Build.TAGS);
        Lg.d("Builder类型：" + Build.TYPE);
        Lg.d("当前开发代号：" + Build.VERSION.CODENAME);
        Lg.d("源码控制版本号：" + Build.VERSION.INCREMENTAL);
        Lg.d("版本字符串：" + Build.VERSION.RELEASE);
        Lg.d("版本号：" + Build.VERSION.SDK_INT);
        Lg.d("Host值：" + Build.HOST);
        Lg.d("User名：" + Build.USER);
        Lg.d("编译时间：" + Build.TIME);
    }

    public void testSys(View v)
    {
        Lg.d("OS版本：" + System.getProperty("os.version"));
        Lg.d("OS名称：" + System.getProperty("os.name"));
        Lg.d("OS架构：" + System.getProperty("os.arch"));
        Lg.d("Home属性：" + System.getProperty("user.home"));
        Lg.d("Name属性：" + System.getProperty("user.name"));
        Lg.d("Dir属性：" + System.getProperty("user.dir"));
        Lg.d("时区：" + System.getProperty("user.timezone"));
        Lg.d("路径分隔符：" + System.getProperty("path.separator"));
        Lg.d("行分隔符：" + System.getProperty("line.separator"));
        Lg.d("文件分隔符：" + System.getProperty("file.separator"));
        Lg.d("Java vender URL属性：" + System.getProperty("java,vendor.url"));
        Lg.d("Java class 路径：" + System.getProperty("java.class.path"));
        Lg.d("Java Class 版本：" + System.getProperty("java.class.version"));
        Lg.d("Java Vender 属性：" + System.getProperty("java.vendor"));
        Lg.d("Java 版本：" + System.getProperty("java.version"));
        Lg.d("Java Home 熟悉：" + System.getProperty("java.home"));
    }

}
