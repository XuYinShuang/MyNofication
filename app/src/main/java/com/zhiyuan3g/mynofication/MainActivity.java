package com.zhiyuan3g.mynofication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnNotification, btnNoficationBar, btnBigPictureStyle, btnBigTextStyle, btnInboxStyle;

    NotificationManager notificationManager;

    //创建一个Notification对象需要创建一个Builder
    Notification.Builder builder = new Notification.Builder(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取manager消息在系统上
        notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        btnBigPictureStyle = (Button) findViewById(R.id.btnBigPictureStyle);
        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnNoficationBar = (Button) findViewById(R.id.btnNoficationBar);
        btnBigTextStyle = (Button) findViewById(R.id.btnBigTextStyle);
        btnInboxStyle = (Button) findViewById(R.id.btnInboxStyle);

        btnBigPictureStyle.setOnClickListener(btnBigPictureStyleListener);
        btnNotification.setOnClickListener(btnNotificationListener);
        btnNoficationBar.setOnClickListener(btnNoficationBarListener);
        btnBigTextStyle.setOnClickListener(btnBigTextStyleListener);
        btnInboxStyle.setOnClickListener(btnInboxStyleeListener);

    }

    View.OnClickListener btnBigPictureStyleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle();
            //内容显示的图片
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.qdoudizhu));

            builder.setSmallIcon(R.drawable.qdoudizhu);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.qdoudizhu));
            builder.setTicker("广告信息").setContentInfo("QQ斗地主");
            builder.setStyle(pictureStyle);
            builder.setAutoCancel(true);
            notificationManager.notify(0, builder.build());


        }
    };
    View.OnClickListener btnNoficationBarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            builder.setSmallIcon(R.drawable.laq)
                    .setTicker("任务下载中").setContentInfo("QQ")
                    .setAutoCancel(true).setContentTitle("QQ下载").setContentText("腾讯科技，用心创造未来");//设置图标

            //模拟下载
            new Thread() {
                @Override
                public void run() {

                    for (int progress = 0; progress < 100; progress += 5) {
                        builder.setProgress(100, progress, false);
                        notificationManager.notify(0, builder.build());

                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            System.out.print("sleep fallure");
                            e.printStackTrace();
                        }
                    }
                    builder.setContentTitle("下载完成").setProgress(0, 0, false).setAutoCancel(true);
                    notificationManager.notify(0, builder.build());

                }
            }.start();


        }
    };
    View.OnClickListener btnBigTextStyleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            bigTextStyle.setBigContentTitle("新闻大爆炸")
                    .setSummaryText("度假，你想去哪？")
                    .bigText("Big Text 云南大理欢迎你");
            builder.setStyle(bigTextStyle);
            builder.setSmallIcon(R.drawable.qdoudizhu);
            builder.setTicker("去哪玩？");
            builder.setContentInfo("腾讯新闻");
            builder.setAutoCancel(true);
            notificationManager.notify(0, builder.build());
        }
    };
    View.OnClickListener btnInboxStyleeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Notification.InboxStyle inBoxStyle=new Notification.InboxStyle();
            inBoxStyle.setBigContentTitle("InboxStyle");
            inBoxStyle.setSummaryText("下载吧");
            for (int i=0;i<5;i++){
                inBoxStyle.addLine("赶紧下载吧"+i);
            }
                builder.setSmallIcon(R.drawable.laq);
            builder.setTicker("下载有惊喜");
            builder.setContentInfo("下载吧就是这么任性");
            builder.setStyle(inBoxStyle);
            builder.setAutoCancel(true);
            notificationManager.notify(0,builder.build());
        }
    };
    View.OnClickListener btnNotificationListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {


            final Intent intent = new Intent(MainActivity.this, Main2Activity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.touxiang);//图标设置
            builder.setWhen(System.currentTimeMillis());//设置通知时间
            builder.setAutoCancel(true);
            builder.setContentTitle("手机短信");//设置通知图标
            builder.setContentText("我使用双节棍吼吼哈嘿");//设置内容
            builder.setTicker("讨债的狗");//通知时标题上显示的内容
            builder.setAutoCancel(true);

            //设置铃声
            //获取系统铃声
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/a.mp3";
            final File file = new File(path);
            builder.setSound(Uri.fromFile(file));
            //获取多媒体的铃声
            builder.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "5"));
            //对builder设置消息文字
            final Notification notification = builder.build();
            notificationManager.notify(0, notification);

        }
    };


}
