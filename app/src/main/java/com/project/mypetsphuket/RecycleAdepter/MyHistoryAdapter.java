package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.BookingInformation;
import com.project.mypetsphuket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyHistoryAdapter extends RecyclerView.Adapter<MyHistoryAdapter.MyViewHolder> {

    //Noww
    Context context;
    List <BookingInformation> bookingInformationList;

    public MyHistoryAdapter(Context context , List<BookingInformation> bookingInformationList){
        this.context = context;
        this.bookingInformationList = bookingInformationList;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Unbinder unbinder;


        @BindView(R.id.txt_hospital_name)
        TextView txt_hospital_name;

        @BindView(R.id.txt_hospital_address)
        TextView txt_hospital_address;

        @BindView(R.id.txt_booking_time_text)
        TextView txt_booking_time_text;

        @BindView(R.id.txt_booking_doctor_text)
        TextView txt_booking_doctor_text;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);;
            unbinder = ButterKnife.bind(this ,itemView);
        }

    }
    @NonNull
    @Override
    public MyHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.hostory_item_layout , parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHistoryAdapter.MyViewHolder holder, int position) {

        holder.txt_booking_doctor_text.setText(bookingInformationList.get(position).getDoctorName());
        holder.txt_booking_time_text.setText(bookingInformationList.get(position).getTime());
        holder.txt_hospital_address.setText(bookingInformationList.get(position).getHospitalAddress());
        holder.txt_hospital_name.setText(bookingInformationList.get(position).getHospitalName());


    }

    @Override
    public int getItemCount() {
        return bookingInformationList.size();
    }
}
