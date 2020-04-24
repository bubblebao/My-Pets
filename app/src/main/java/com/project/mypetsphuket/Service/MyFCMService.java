package com.project.mypetsphuket.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.project.mypetsphuket.Prevalent.Prevalent;

public class MyFCMService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
     //   Log.d("TAG", "the token ref: ",)
          super.onNewToken(s);
          Prevalent.updateToket(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
