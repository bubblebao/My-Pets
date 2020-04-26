package com.project.mypetsphuket.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.project.mypetsphuket.Prevalent.Prevalent;

import java.util.Random;

import io.paperdb.Paper;

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

        /*Prevalent.showNotification(this , new Random().nextInt(),
                remoteMessage.getData().get(Prevalent.TITTLE_KEY),
                remoteMessage.getData().get(Prevalent.CONTENT_KEY),
                null);
        */

        if (remoteMessage.getData()!=null){
            if (remoteMessage.getData().get("update_done")!=null){

                updateLastBooking();
            }
            if (remoteMessage.getData().get(Prevalent.TITTLE_KEY)!= null&&
                remoteMessage.getData().get(Prevalent.CONTENT_KEY)!=null){


            }
       /*     Prevalent.showNotification(this , new Random().nextInt(),
                    remoteMessage.getData().get(Prevalent.TITTLE_KEY),
                    remoteMessage.getData().get(Prevalent.CONTENT_KEY),
                    null);
       */
        }
    }

    private void updateLastBooking() {



        CollectionReference userBooking;
        if (Prevalent.currentOnlineUser != null){

            userBooking = FirebaseFirestore.getInstance()
                    .collection("User")
                    .document(Prevalent.currentOnlineUser.getPhone())
                    .collection("Booking");
        }else {
            //App not run
            Paper.init(this);
            String user = Paper.book().read(Prevalent.LOGGED_KEY);

            userBooking = FirebaseFirestore.getInstance()
                    .collection("User")
                    .document(user)
                    .collection("Booking");
        }


    }
}
