package com.project.mypetsphuket.Spare.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class HospitalViewHolder extends RecyclerView.ViewHolder {

    public TextView txtHospitalName, txtHospitalDescription, txtHospitalLocation;
    public ImageView imageView;
   // public ItemClickListner listner;
    View hView;

    public HospitalViewHolder(@NonNull View itemView) {
        super(itemView);

        hView = itemView;

 /*       itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hClickListener.onItemClick(view, getAdapterPosition());


            }
        });


        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                hClickListener.onItemLongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setDetail(Context ctx  , String Name , String Description , String location, String image ){

    /*    ImageView HospitalImage = hView.findViewById(R.id.Hospital_image);
        TextView HospitalName = hView.findViewById(R.id.Hospital_name);
        TextView HospitalDescription = hView.findViewById(R.id.Hospital_description);
        TextView HospitalLocation = hView.findViewById(R.id.Hospital_location);

        HospitalName.setText(Name);
        HospitalDescription.setText(Name);
        HospitalLocation.setText(Name);

        Picasso.get().load(image).into(HospitalImage);

    }

    private HospitalViewHolder.ClickListener hClickListener;

    public interface ClickListener {

        void onItemClick(View view , int position);
        void onItemLongClick(View view,int position);


    }

    public void setOnClickListener (HospitalViewHolder.ClickListener clickListener ){

        hClickListener = clickListener;


  */  }


}






