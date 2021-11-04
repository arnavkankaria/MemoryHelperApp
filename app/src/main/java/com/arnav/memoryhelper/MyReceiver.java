package com.arnav.memoryhelper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")){

            NotificationCompat.Builder builder;
            String channelId = "Default";
            String NOTIFICATION_CHANNEL_ID = "10001" ;
            String default_notification_channel_id = "default" ;

            Intent resultIntent = new Intent(context, MainActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(context,100,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.cerclebackgroundyello)
                    .setColor(context.getResources().getColor(R.color.colorAccent))
                    .setContentTitle("It's Medicine time")
                    .setContentText("Take your medicines")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentText("Medicine").setAutoCancel(true).setContentIntent(resultPendingIntent);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager. IMPORTANCE_HIGH ;
                NotificationChannel notificationChannel = new
                        NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                assert mNotificationManager != null;
                mNotificationManager.createNotificationChannel(notificationChannel) ;

            }

            mNotificationManager.notify(0, builder.build());
        }

    }
}
