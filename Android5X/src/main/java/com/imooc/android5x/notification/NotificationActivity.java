package com.imooc.android5x.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.imooc.android5x.R;

public class NotificationActivity extends Activity
{
    private static final int NOTIFICATION_ID_NORMAL = 1001;
    private static final int NOTIFICATION_ID_FOLD = 1002;
    private static final int NOTIFICATION_ID_HAND = 1003;
    private static final int NOTIFICATION_ID_GREAD = 1004;
    private Context mContext;
    private NotificationManager mNotificationManager;
    private Button mBtn_testNormalNotification;
    private Button mBtn_testFoldNotification;
    private Button mBtn_testHandNotification;
    private Button mBtn_testGreadNotification_private;
    private Button mBtn_testGreadNotification_public;
    private Button mBtn_testGreadNotification_secret;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.mContext = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initViews();
        initEvent();
    }

    private void initViews()
    {
        mBtn_testNormalNotification = findViewById(R.id.btn_testNormal);
        mBtn_testFoldNotification = findViewById(R.id.btn_testFold);
        mBtn_testHandNotification = findViewById(R.id.btn_testHand);

        mBtn_testGreadNotification_private = findViewById(R.id.btn_testGread_private);
        mBtn_testGreadNotification_public = findViewById(R.id.btn_testGread_public);
        mBtn_testGreadNotification_secret = findViewById(R.id.btn_testGread_secret);
    }

    private void initEvent()
    {
        View.OnClickListener btnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.btn_testNormal:
                        testNormal();
                        break;
                    case R.id.btn_testFold:
                        testFolde();
                        break;
                    case R.id.btn_testHand:
                        testHand();
                        break;
                    default:
                        break;
                }
            }
        };
        mBtn_testNormalNotification.setOnClickListener(btnClickListener);
        mBtn_testFoldNotification.setOnClickListener(btnClickListener);
        mBtn_testHandNotification.setOnClickListener(btnClickListener);
        View.OnClickListener btnClickGreadListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Notification.Builder builder = new Notification.Builder(mContext);
                builder.setContentTitle("Notification for Visibility Test");
                builder.setContentText(((Button) view).getText());
                builder.setSmallIcon(R.drawable.ic_launcher);
                switch (view.getId())
                {
                    case R.id.btn_testGread_private:
                        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                        builder.setColor(Color.RED);
                        builder.setCategory(Notification.CATEGORY_MESSAGE);
                        break;
                    case R.id.btn_testGread_public:
                        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                        builder.setColor(Color.GREEN);
                        builder.setCategory(Notification.CATEGORY_EMAIL);

                        break;
                    case R.id.btn_testGread_secret:
                        builder.setVisibility(Notification.VISIBILITY_SECRET);
                        builder.setCategory(Notification.CATEGORY_CALL);
                        builder.setColor(Color.BLUE);
                        break;
                }
                mNotificationManager.notify(NOTIFICATION_ID_GREAD, builder.build());

            }
        };
        mBtn_testGreadNotification_public.setOnClickListener(btnClickGreadListener);
        mBtn_testGreadNotification_private.setOnClickListener(btnClickGreadListener);
        mBtn_testGreadNotification_secret.setOnClickListener(btnClickGreadListener);
    }

    /**
     * 普通
     */
    private void testNormal()
    {
        Notification.Builder builder = new Notification.Builder(mContext);
        //设置属性
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher));
        builder.setContentTitle("Normal Notification");
        builder.setContentText("I am normal custom_notification_layout");
        builder.setSubText("it is really basic");
        //设置点击事件
        Intent it = new Intent();
        it.setAction(Intent.ACTION_VIEW);
        it.setData(Uri.parse("http://www.baidu.com"));
        PendingIntent pt = PendingIntent.getActivity(mContext, 0, it, 0);
        builder.setContentIntent(pt);
        //展示
        mNotificationManager.notify(NOTIFICATION_ID_NORMAL, builder.build());
    }

    /**
     * 折叠
     */
    private void testFolde()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        //通过RemoteViews来创建自定义的Notification视图
        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.custom_notification_layout);
        contentView.setTextViewText(R.id.textView, "哈哈");
        Notification notification = builder.build();

        notification.contentView = contentView;
        // 通过RemoteViews来创建自定义的Notification视图
        RemoteViews expandedView = new RemoteViews(mContext.getPackageName(), R.layout.custom_notification_expanded_layout);
        notification.bigContentView = expandedView;
        mNotificationManager.notify(NOTIFICATION_ID_FOLD, notification);
    }

    /**
     * 悬挂
     */
    private void testHand()
    {
        Notification.Builder builder = new Notification.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher).
                setContentTitle("Hand Notification").setContentText("I am hand custom_notification_layout");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(pendingIntent, true);
        mNotificationManager.notify(NOTIFICATION_ID_HAND, builder.build());
    }

}
