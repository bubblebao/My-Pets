package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.TimeSlot;
import com.project.mypetsphuket.Prevalent.Prevalent;
import com.project.mypetsphuket.R;

import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_time_slot;
        TextView txt_time_slot_decription;
        CardView card_time_slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = (CardView) itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView)  itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_decription = (TextView) itemView.findViewById(R.id.txt_time_slot_description);
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
                    holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.dark_grey));
                    holder.txt_time_slot_decription.setText("FULL");
                    holder.txt_time_slot_decription.setTextColor(context.getResources().getColor(R.color.white));
                    holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));


                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return Prevalent.TIME_SLOT_TOTAL;
    }



/*    Context context;
    List<TimeSlot> timeSlotList;

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
    }

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_time_slot;
        TextView txt_time_slot_decription;
        CardView card_time_slot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_time_slot = itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_decription = itemView.findViewById(R.id.txt_time_slot_description);
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
        holder.txt_time_slot.setText(new StringBuilder(Prevalent.convertTimetoString(position)).toString());
        if (timeSlotList.size()==0)  //open All
        {
            holder.txt_time_slot_decription.setText("Available");
            holder.txt_time_slot_decription.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
            holder.txt_time_slot.setTextColor(context.getResources()
                    .getColor(android.R.color.black));
            holder.card_time_slot.setCardBackgroundColor(context.getResources()
                    .getColor(R.color.white));

        }
        else //position Full
        {
            for (TimeSlot slotValue:timeSlotList){
                int slot = Integer.parseInt(slotValue.getSlot().toString());
                if(slot == position){
                    holder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.dark_grey));
                    holder.txt_time_slot_decription.setText("FULL");
                    holder.txt_time_slot_decription.setTextColor(context.getResources().getColor(R.color.white));
                    holder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));


                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return Prevalent.TIME_SLOT_TOTAL;
    }

*/
}
