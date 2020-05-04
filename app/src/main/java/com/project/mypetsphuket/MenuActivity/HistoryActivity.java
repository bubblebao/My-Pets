package com.project.mypetsphuket.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.Model.UserBookingLoadEvent;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyHistoryAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class HistoryActivity extends AppCompatActivity {



    @BindView(R.id.register_history)
    RecyclerView register_history;

    @BindView(R.id.txt_history)
    TextView txt_history;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);
        init();
        initView();

        loadUserBookingInformation();
    }

    private void loadUserBookingInformation() {
            dialog.show();

            //User/+66822828303/Booking/
        CollectionReference userBooking = FirebaseFirestore.getInstance()
                .collection("User")
                .document(Prevalent.currentOnlineUser.getPhone())
                .collection("Booking");

        userBooking.whereEqualTo("done",true)
                .orderBy("timestamp" , Query.Direction.DESCENDING)
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        EventBus.getDefault().post(new UserBookingLoadEvent(false,
                                e.getMessage()));
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<BookingInformation> bookingInformationList = new ArrayList<>();
                    for(DocumentSnapshot userBookingSnapshot:task.getResult()){
                        BookingInformation bookingInformation = userBookingSnapshot.toObject(BookingInformation.class);
                        bookingInformationList.add(bookingInformation);
                    }
                    //Event Bus Send Data
                    EventBus.getDefault().post(new UserBookingLoadEvent(true , bookingInformationList) );
                }
            }
        });
    }

    private void initView() {

        register_history.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        register_history.setLayoutManager(layoutManager);
        register_history.addItemDecoration(new DividerItemDecoration(this , layoutManager.getOrientation()));

    }

    private void init() {
        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode =  ThreadMode.MAIN)
    public void diaplayData(UserBookingLoadEvent event){
        if (event.isSuccess()){
            MyHistoryAdapter adapter = new MyHistoryAdapter(this,event.getBookingInformationList());
            register_history.setAdapter(adapter);

            txt_history.setText(new StringBuilder("HISTORY(")
            .append(event.getBookingInformationList().size())
            .append(")"));

        }else {

            Toast.makeText(this,""+event.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
    }
}
