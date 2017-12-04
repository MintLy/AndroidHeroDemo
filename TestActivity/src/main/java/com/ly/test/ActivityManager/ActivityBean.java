package com.ly.test.ActivityManager;

/**
 * Created by Mint on 2017/11/26.
 */

public class ActivityBean
{
    private String pid;
    private String uid;
    private String memorySize;
    private String processName;

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getMemorySize()
    {
        return memorySize;
    }

    public void setMemorySize(String memorySize)
    {
        this.memorySize = memorySize;
    }

    public String getProcessName()
    {
        return processName;
    }

    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"pid\":\"")
          .append(pid).append('\"');
        sb.append(",\"uid\":\"")
          .append(uid).append('\"');
        sb.append(",\"memorySize\":\"")
          .append(memorySize).append('\"');
        sb.append(",\"processName\":\"")
          .append(processName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
