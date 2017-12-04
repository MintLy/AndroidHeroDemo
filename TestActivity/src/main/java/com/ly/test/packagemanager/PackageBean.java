package com.ly.test.packagemanager;

import android.graphics.drawable.Drawable;

/**
 * Created by Mint on 2017/11/26.
 */

public class PackageBean
{
    private String appLable;
    private Drawable appIcon;
    private String pkgName;

    public String getAppLable()
    {
        return appLable;
    }

    public void setAppLable(String appLable)
    {
        this.appLable = appLable;
    }

    public Drawable getAppIcon()
    {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon)
    {
        this.appIcon = appIcon;
    }

    public String getPkgName()
    {
        return pkgName;
    }

    public void setPkgName(String pkgName)
    {
        this.pkgName = pkgName;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"appLable\":\"")
          .append(appLable).append('\"');
        sb.append(",\"appIcon\":")
          .append(appIcon);
        sb.append(",\"pkgName\":\"")
          .append(pkgName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}