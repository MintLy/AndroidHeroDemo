package com.ly.test.packagemanager;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ly.test.R;

import java.util.ArrayList;
import java.util.List;

public class PackageManagerActivity extends Activity
{
    private static final String TAG = "fuck";
    private final int ALL_APP = 1001;
    private final int SYSTEM_APP = 1002;
    private final int THIRD_APP = 1003;
    private final int SDCARD_APP = 1004;
    private Context mContext;
    private PackageManager mPackageManager;
    private List<PackageBean> mPckageBeans = new ArrayList<PackageBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_manager);
        this.mContext = this.getApplicationContext();
        mPackageManager = mContext.getPackageManager();
    }

    public void getAllApp(View v)
    {
        List<PackageBean> appinfo = getAppinfo(ALL_APP);
        for (PackageBean app : appinfo)
        {
            Log.d(TAG, "getAllApp: " + app.toString());
        }
    }

    public void getSystemApp(View v)
    {
        List<PackageBean> appinfo = getAppinfo(SYSTEM_APP);
        for (PackageBean app : appinfo)
        {
            Log.d(TAG, "getSystemApp: " + app.toString());
        }
    }

    public void getThirdApp(View v)
    {
        List<PackageBean> appinfo = getAppinfo(THIRD_APP);
        for (PackageBean app : appinfo)
        {
            Log.d(TAG, "getThirdApp: " + app.toString());
        }
    }

    public void getSdcardApp(View v)
    {
        List<PackageBean> appinfo = getAppinfo(SDCARD_APP);
        for (PackageBean app : appinfo)
        {
            Log.d(TAG, "getSdcardApp: " + app.toString());
        }
        Log.d(TAG, "getSdcardApp: ");
    }

    private List<PackageBean> getAppinfo(int flag)
    {
        List<ApplicationInfo> installedApplications = mPackageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        mPckageBeans.clear();
        switch (flag)
        {
            case ALL_APP:
                for (ApplicationInfo app : installedApplications)
                {
                    mPckageBeans.add(makeAppinfo(app));
                }
                break;
            case SYSTEM_APP:
                for (ApplicationInfo app : installedApplications)
                {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
                    {
                        mPckageBeans.add(makeAppinfo(app));
                    }
                }
                break;
            case THIRD_APP:
                for (ApplicationInfo app : installedApplications)
                {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)
                    {
                        mPckageBeans.add(makeAppinfo(app));
                    }
                    else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0)
                    {
                        mPckageBeans.add(makeAppinfo(app));
                    }
                }
                break;
            case SDCARD_APP:
                for (ApplicationInfo app : installedApplications)
                {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0)
                    {
                        mPckageBeans.add(makeAppinfo(app));
                    }
                }
                break;
            default:
                return null;
        }
        return mPckageBeans;
    }

    private PackageBean makeAppinfo(ApplicationInfo ai)
    {
        PackageBean ab = new PackageBean();
        ab.setAppLable(ai.loadLabel(mPackageManager).toString());
        ab.setAppIcon(ai.loadIcon(mPackageManager));
        ab.setPkgName(ai.packageName);
        return ab;
    }
}
