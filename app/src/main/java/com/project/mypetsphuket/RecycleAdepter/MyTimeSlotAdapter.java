package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Interface.IRecycleItemSelectedListener;
import com.project.mypetsphuket.Model.TimeSlot;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;


    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
        cardViewList = new ArrayList<>();
    }

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
        cardViewList = new ArrayList<>();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_time_slot;
        TextView txt_time_slot_decription;
        CardView card_time_slot;

        IRecycleItemSelectedListener iRecycleItemSelectedListener;

        public void setiRecycleItemSelectedListener(IRecycleItemSelectedListener iRecycleItemSelectedListener) {
            this.iRecycleItemSelectedListener = iRecycleItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = (CardView) itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView)  itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_decription = (TextView) itemView.findViewById(R.id.txt_time_slot_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            iRecycleItemSelectedListener.onItemSelectedListener(v , getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.timeslot_item_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_time_slot.setText(new StringBuilder(Prevalent.convertTimeSlotToString(position)).toString());
        if (timeSlotList.size()==0)  //open All
        {
            holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.txt_time_slot_decription.setText("Available");
            holder.txt_time_slot_decription.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
            holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

        }
        else //position Full
        {
            for (TimeSlot slotValue : timeSlotList) {
                int slot = Integer.parseInt(slotValue.getSlot().toString());
                if (slot == position) {
                    holder.card_time_slot.setTag(Prevalent.DISABLE_TAG);
                    holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.dark_grey));
                    holder.txt_time_slot_decription.setText("FULL");
                    holder.txt_time_slot_decription.setTextColor(context.getResources().getColor(R.color.white));
                    holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));


                }
            }
        }
        //Add all card
        if (!cardViewList.contains(holder.card_time_slot))
            cardViewList.add(holder.card_time_slot);


        //Check only available time slot
        holder.setiRecycleItemSelectedListener(new IRecycleItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int position) {
            //Loop for all card
                for (CardView cardView :cardViewList) {
                    if (cardView.getTag() == null)  //only enable
                        cardView.setCardBackgroundColor(context.getResources()
                                .getColor(android.R.color.white));
                }
                //Our select
                holder.card_time_slot.setCardBackgroundColor(context.getResources()
                        .getColor(android.R.color.holo_orange_dark));;

                //After send broadcast

                Intent intent = new Intent(Prevalent.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Prevalent.KEY_TIME_SLOT,position);
                intent.putExtra(Prevalent.KEY_STEP,3);
                localBroadcastManager.sendBroadcast(intent);
            }
            //chaang card when select card
        });
    }

    @Override
    public int getItemCount() {
        return Prevalent.TIME_SLOT_TOTAL;
    }




}
