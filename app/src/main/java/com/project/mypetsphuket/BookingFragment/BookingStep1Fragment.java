package com.project.mypetsphuket.BookingFragment;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.mypetsphuket.Interface.AllServiceLoadListener;
import com.project.mypetsphuket.Interface.IAllAreaLoadListener;
import com.project.mypetsphuket.Interface.IBranchLoadListener;
import com.project.mypetsphuket.Model.BookingHospitals;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.project.mypetsphuket.RecycleAdepter.MyHospitalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;


public class BookingStep1Fragment extends Fragment implements AllServiceLoadListener, IBranchLoadListener, IAllAreaLoadListener {


    //Variable
    CollectionReference allAreaRef;
    CollectionReference branchRef;

    IAllAreaLoadListener iAllAreaLoadListener;
    IBranchLoadListener iBranchLoadListener;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.RecycleView_hospital_Book)
    RecyclerView recycler_book;

    Unbinder unbinder;
    AlertDialog dialog;


    static BookingStep1Fragment instance;

    public static BookingStep1Fragment getInstance(){
        if (instance == null)
            instance = new BookingStep1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false)
                .build();
        allAreaRef = FirebaseFirestore.getInstance().collection("AllArea");
        iAllAreaLoadListener = this;
        iBranchLoadListener = this;



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_booking_step1, container, false);
        unbinder = ButterKnife.bind(this,itemView);

        initView();
        loadAllArea();
        return itemView;
    }

    private void initView() {



        recycler_book.setHasFixedSize(true);
        recycler_book.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_book.addItemDecoration(new SPItemDecoration(4));
    }

    private void loadAllArea() {
          allAreaRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            List<String> list = new  ArrayList<>();
                            list.add("Please Choose your area");
                            for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                                list.add(documentSnapshot.getId());
                            iAllAreaLoadListener.onAllServiceLoadSuccess(list);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    iAllAreaLoadListener.onAllServiceLoadFailed(e.getMessage());
                }
            });
    }

    @Override
    public void onAllServiceLoadSuccess(List<String> areaNameList) {
        spinner.setItems(areaNameList);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position>0){

                    loadBranchofArea(item.toString());
                }else
                    recycler_book.setVisibility(View.GONE);
            }
        });
    }

    private void loadBranchofArea(String cityName) {

        dialog.show();
        Prevalent.city = cityName;

        branchRef = FirebaseFirestore.getInstance()
                .collection("AllArea")
                .document(cityName)
                .collection("Branch");

        branchRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                List<BookingHospitals> list = new ArrayList<>();
                if (task.isSuccessful()){

                    for (QueryDocumentSnapshot documentSnapshot:task.getResult()) {

                        BookingHospitals bookingHospitals = documentSnapshot.toObject(BookingHospitals.class);
                        bookingHospitals.setHospitalId(documentSnapshot.getId());
                        list.add(bookingHospitals);
                    }

                    iBranchLoadListener.onIBranchLoadSuccess(list);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iBranchLoadListener.onIBranchLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllServiceLoadFailed(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIBranchLoadSuccess(List<BookingHospitals> NameList) {
        MyHospitalAdapter adapter = new MyHospitalAdapter(getActivity(),NameList);
        recycler_book.setAdapter(adapter);
        recycler_book.setVisibility(View.VISIBLE);

        dialog.dismiss();


    }

    @Override
    public void onIBranchLoadFailed(String message) {

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
