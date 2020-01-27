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
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Interface.IRecycleItemSelectedListener;
import com.project.mypetsphuket.Model.DoctorAndHospital;
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    Context context;
    List<DoctorAndHospital> doctorAndHospitalList;
    List<CardView> cardViewsList;
    LocalBroadcastManager localBroadcastManager;

    public MyItemAdapter(Context context, List<DoctorAndHospital> doctorAndHospitalList) {
        this.context = context;
        this.doctorAndHospitalList = doctorAndHospitalList;
        cardViewsList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_items_layout, parent ,false );
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_item_name , txt_item_address;
        ImageView book_ImagView;
        RatingBar ratingBar;
        CardView card_book;

        IRecycleItemSelectedListener iRecycleItemSelectedListener;

        public IRecycleItemSelectedListener getiRecycleItemSelectedListener() {
            return iRecycleItemSelectedListener;
        }

        public void setiRecycleItemSelectedListener(IRecycleItemSelectedListener iRecycleItemSelectedListener) {
            this.iRecycleItemSelectedListener = iRecycleItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_name =  itemView.findViewById(R.id.txt_book_name);
            card_book = (CardView) itemView.findViewById(R.id.card_booking);

            book_ImagView = (ImageView) itemView.findViewById(R.id.Book_imageView);
            txt_item_name =  itemView.findViewById(R.id.txt_book_name);

            ratingBar = itemView.findViewById(R.id.rtb_booking);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycleItemSelectedListener.onItemSelectedListener(v ,getAdapterPosition());
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_item_name.setText(doctorAndHospitalList.get(position).getName());
        holder.ratingBar.setRating(doctorAndHospitalList.get(position).getRating());
        Picasso.get().load(doctorAndHospitalList.get((position)).getUrl()).into(holder.book_ImagView);


        if (!cardViewsList.contains(holder.card_book))
            cardViewsList.add(holder.card_book);

        holder.setiRecycleItemSelectedListener(new IRecycleItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int position) {
                //White Backgrond All Card

                for (CardView cardView:cardViewsList)
                    cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

                //Set Select
                holder.card_book.setCardBackgroundColor(context.getResources().getColor(android.R.color.holo_purple));

                //Send Broadcast to booking enable
                Intent intent = new Intent(Prevalent.KEY_ENABLE_NEXT);
                intent.putExtra(Prevalent.KEY_ITEM_STORE , doctorAndHospitalList.get(position));
                localBroadcastManager.sendBroadcast(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorAndHospitalList.size();
    }


}
