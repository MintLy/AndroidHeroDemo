package com.ly.test.ActivityManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.ly.test.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityManagerActivity extends Activity
{
    private static final String TAG = "fuck";
    private Context mContext;
    private ActivityManager mActivityManager;
    private List<ActivityBean> mActivityBeans = new ArrayList<ActivityBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        this.mContext = getApplicationContext();
        mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
    }

    public void show(View v)
    {
        List<ActivityBean> runningProcessInfo = getRunningProcessInfo();
        for (int i = 0; i < runningProcessInfo.size(); i++)
        {
            Log.d(TAG, "show: " + runningProcessInfo.get(i));
        }
    }

    private List<ActivityBean> getRunningProcessInfo()
    {
        mActivityBeans.clear();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++)
        {
            ActivityManager.RunningAppProcessInfo appInfo = runningAppProcesses.get(i);
            int pid = appInfo.pid;
            int uid = appInfo.uid;
            String processName = appInfo.processName;
            int[] memoryPid = new int[]{pid};
            Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(memoryPid);
            int totalPss = memoryInfo[0].getTotalPss();
            ActivityBean bean = new ActivityBean();
            bean.setPid(pid + "");
            bean.setUid(uid + "");
            bean.setMemorySize(totalPss + "");
            bean.setProcessName(processName);
            mActivityBeans.add(bean);
        }
        return mActivityBeans;
    }
}
