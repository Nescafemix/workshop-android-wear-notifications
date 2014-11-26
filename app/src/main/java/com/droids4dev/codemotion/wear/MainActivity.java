package com.droids4dev.codemotion.wear;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    private static final String GROUP_KEY_WORKSHOP_WEAR = "group_key_workshop_wear";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateDefaultNotification();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateSimpleWearNotification();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateWearNotificationWithPages();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateWearNotificationWithPagesWithDefaultStyles();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateWearNotificationWithActionButton();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateWearNotificationWithSeveralActionButtons();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateStackOfNotifications();
            }
        });

    }

    private NotificationCompat.Builder getBasicNotificationBuilder() {
        return new NotificationCompat.Builder(this);
    }

    private void generateDefaultNotification() {
            int notificationId = 1;
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("Default title")
                    .setContentText("Default text.")
                    .setTicker("New notification!");


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateSimpleWearNotification() {
        int notificationId = 1;

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.trollface));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title")
                .setContentText("Default text.")
                .setTicker("New notification!")
                .extend(extender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateWearNotificationWithPages() {
        int notificationId = 1;

        // Create a second page notification
        Notification secondPageNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Second Page title")
                .setContentText("Default second page text")
                .build();

        // Specific extender to show only background in this notification page
        NotificationCompat.Extender extenderOnlyImage = new NotificationCompat.WearableExtender()
                .setHintShowBackgroundOnly(true);

        // Create a third page notification with only background
        Notification thirdPageNotification = new NotificationCompat.Builder(this)
                .extend(extenderOnlyImage)
                .build();

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.trollface))
                .addPage(secondPageNotification)
                .addPage(thirdPageNotification);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title")
                .setContentText("Default text.")
                .setTicker("New notification!")
                .extend(extender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateWearNotificationWithPagesWithDefaultStyles() {
        int notificationId = 1;

        // Create a big text style for the second page
        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle()
                .setBigContentTitle("BigTextStyle title")
                .bigText(getString(R.string.a_very_large_text));

        // Create a Inbox style for the third page
        NotificationCompat.InboxStyle thirdPageStyle = new NotificationCompat.InboxStyle()
                .setBigContentTitle("InboxStyle title")
                .addLine("Line 1")
                .addLine("Line 2")
                .addLine("Line 3");

        // Create a BigPicture style for the fourth page
        NotificationCompat.BigPictureStyle fourthPageStyle = new NotificationCompat.BigPictureStyle()
                .setBigContentTitle("BigPictureStyle title")
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.trollface));

        // Create second page notification
        Notification secondPageNotification = new NotificationCompat.Builder(this)
                .setStyle(secondPageStyle)
                .build();

        // Create third page notification
        Notification thirdPageNotification = new NotificationCompat.Builder(this)
                .setStyle(thirdPageStyle)
                .build();

        // Create fourth page notification
        Notification fourthPageNotification = new NotificationCompat.Builder(this)
                .setStyle(fourthPageStyle)
                .build();

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .addPage(secondPageNotification)
                .addPage(thirdPageNotification)
                .addPage(fourthPageNotification);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title")
                .setContentText("Default text.")
                .setTicker("New notification!")
                .extend(extender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateWearNotificationWithActionButton() {
        int notificationId = 1;
        int requestCode = 0;

        // Create an intent to open DeilActivity action
        Intent actionIntent = new Intent(this, DetailActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this,requestCode,
                actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_view,
                "Open detail",
                actionPendingIntent).build();

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.trollface));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title")
                .setContentText("Default text.")
                .setTicker("New notification!")
                .setContentIntent(actionPendingIntent)
                .addAction(action)
                .extend(extender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateWearNotificationWithSeveralActionButtons() {
        int notificationId = 1;
        int requestCode = 0;

        // Create an intent to open DeilActivity action
        Intent actionIntent = new Intent(this, DetailActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, requestCode,
                actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_view,
                "Open detail",
                actionPendingIntent).build();

        RemoteInput voiceRemoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel("Reply by voice")
                .setChoices(getResources().getStringArray(R.array.reply_choices))
                .build();

        // Create an intent to open the ShowMessageActivity action
        Intent showMessageIntent = new Intent(this, ShowMessageActivity.class);
        PendingIntent showMessagePendingIntent = PendingIntent.getActivity(this, ++requestCode,
                showMessageIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the ReplyByVoiceAction and add the remote input
        NotificationCompat.Action replyVoiceAction = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_add,
                "Speak now",
                showMessagePendingIntent)
                .addRemoteInput(voiceRemoteInput)
                .build();

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.trollface))
                .addAction(replyVoiceAction);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title")
                .setContentText("Default text.")
                .setTicker("New notification!")
                .setContentIntent(actionPendingIntent)
                .addAction(action)
                .extend(extender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void generateStackOfNotifications() {
        int notification1Id = 1;
        int notification2Id = 2;
        int notificationSummaryId = 3;
        NotificationCompat.Builder notification1Builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title 1")
                .setContentText("Default text 1.")
                .setTicker("New notification: 1!")
                .setGroup(GROUP_KEY_WORKSHOP_WEAR);

        NotificationCompat.Builder notification2Builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Default title 2")
                .setContentText("Default text 2.")
                .setTicker("New notification: 2!")
                .setGroup(GROUP_KEY_WORKSHOP_WEAR);

        NotificationCompat.Extender extender = new NotificationCompat.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.trollface));

        NotificationCompat.Builder notificationSummaryBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Summary title")
                .setContentText("Sumary description")
                .setTicker("New notification!")
                .extend(extender)
                .setGroup(GROUP_KEY_WORKSHOP_WEAR)
                .setGroupSummary(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notification1Id, notification1Builder.build());
        notificationManager.notify(notification2Id, notification2Builder.build());
        notificationManager.notify(notificationSummaryId, notificationSummaryBuilder.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
