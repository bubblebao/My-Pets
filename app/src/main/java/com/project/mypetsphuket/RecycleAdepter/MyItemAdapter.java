package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.DoctorAndHospital;
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.R;

import java.util.ArrayList;
import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    Context context;
    List<DoctorAndHospital> doctorAndHospitalList;

    public MyItemAdapter(Context context, List<DoctorAndHospital> doctorAndHospitalList) {
        this.context = context;
        this.doctorAndHospitalList = doctorAndHospitalList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_items_layout, parent ,false );
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_name , txt_item_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_name =  itemView.findViewById(R.id.txt_book_name);
            txt_item_address = itemView.findViewById(R.id.txt_book_location);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_item_name.setText(doctorAndHospitalList.get(position).getName());
        holder.txt_item_address.setText(doctorAndHospitalList.get(position).getAddress());



    }

    @Override
    public int getItemCount() {
        return doctorAndHospitalList.size();
    }


}
