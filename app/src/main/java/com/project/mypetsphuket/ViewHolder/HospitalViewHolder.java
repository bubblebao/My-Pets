package com.project.mypetsphuket.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.R;

import org.w3c.dom.Text;

public class HospitalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtHospitalName, txtHospitalDescription, txtHospitalLocation;
    public ImageView imageView;
    public ItemClickListner listner;

    public HospitalViewHolder(@NonNull View itemView) {


        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.Hospital_image);
        txtHospitalDescription = (TextView) itemView.findViewById(R.id.Hospital_description);
        txtHospitalLocation = (TextView) itemView.findViewById(R.id.Hospital_location);


    }
    public void setItemClickListner(ItemClickListner listner){

        this.listner = listner;

    }


    @Override
    public void onClick(View view) {

        listner.onClick(view, getAdapterPosition(), false);

    }

}
