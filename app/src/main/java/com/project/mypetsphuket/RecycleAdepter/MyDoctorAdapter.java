package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Interface.IRecycleItemSelectedListener;
import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyDoctorAdapter extends RecyclerView.Adapter<MyDoctorAdapter.MyViewHolder> {

    Context context;
    List<BookingDoctor> bookingDoctorList;
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;

    public MyDoctorAdapter(Context context, List<BookingDoctor> bookingDoctorList) {
        this.context = context;
        this.bookingDoctorList = bookingDoctorList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(context)
               .inflate(R.layout.book_doctor_layout ,parent,false );
        return new MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_doctor_name ;
        ImageView doctor_book_ImagView;
        RatingBar doctor_RatingBar;
        CardView card_doctor;

        IRecycleItemSelectedListener iRecycleItemSelectedListener;

        public void setiRecycleItemSelectedListener(IRecycleItemSelectedListener iRecycleItemSelectedListener) {
            this.iRecycleItemSelectedListener = iRecycleItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doctor_book_ImagView = (ImageView) itemView.findViewById(R.id.book_doctor_imageView);
            txt_doctor_name = (TextView) itemView.findViewById(R.id.txt_book_doctor_name);
            doctor_RatingBar = (RatingBar) itemView.findViewById(R.id.rtb_booking_doctor);
            card_doctor = (CardView) itemView.findViewById(R.id.card_booking_doctor);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycleItemSelectedListener.onItemSelectedListener(v , getAdapterPosition());

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_doctor_name.setText(bookingDoctorList.get(position).getName());
        holder.doctor_RatingBar.setRating((float)bookingDoctorList.get(position).getRating());
        Picasso.get().load(bookingDoctorList.get(position).getUrl()).into(holder.doctor_book_ImagView);

        if (!cardViewList.contains(holder.card_doctor))
            cardViewList.add(holder.card_doctor);

            holder.setiRecycleItemSelectedListener(new IRecycleItemSelectedListener() {
                @Override
                public void onItemSelectedListener(View view, int position) {

                    //White Backgrond All item Card
                    for (CardView cardView:cardViewList)

                        cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

                    //Set Backgrond for Select
                    holder.card_doctor.setCardBackgroundColor(context.getResources()
                            .getColor(android.R.color.holo_orange_dark));

                    //Send local Broadcast to button next enable
                    Intent intent = new Intent(Prevalent.KEY_ENABLE_BUTTON_NEXT);
                    intent.putExtra(Prevalent.KEY_DOCTOR_SELECTED , bookingDoctorList.get(position));
                    intent.putExtra(Prevalent.KEY_STEP,2);
                    localBroadcastManager.sendBroadcast(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return bookingDoctorList.size();
    }

}
