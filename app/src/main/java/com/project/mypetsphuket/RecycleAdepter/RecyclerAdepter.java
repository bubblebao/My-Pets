package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.DelailsActivity.HospitalsDetailActivity;
import com.project.mypetsphuket.DelailsActivity.InformationsActivity;
import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.Model.Hospitals;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdepter extends RecyclerView.Adapter<RecyclerAdepter.ViewHolder>  {

    private static final String TAG = "RecyclerAdepter";
    private Context mContext;
    private ArrayList<Hospitals> hospitalList;


    public RecyclerAdepter(Context context , ArrayList <Hospitals> hospitalList){

        this.mContext = context;
        this.hospitalList= hospitalList;
    }

    @NonNull
    @Override
    public RecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_items_layout,parent,false);
        return new ViewHolder(view);
    }

            //**    1. implements View.OnClickListener  *//
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // 2. Defined parameter
        ItemClickListner itemClickListner;
        ImageView imageView;
        TextView  HospitalName;
        TextView  HospitalDescription;
        TextView  Hospitalservicetype;
        TextView  Hospitalservicetime;
        TextView  HospitalLocation;
        TextView  HospitalRating;

        public ViewHolder(@NonNull View itemView) {

            //Find Object in RecycleView
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Hospital_ImageView);
            HospitalName = (TextView) itemView.findViewById(R.id.Hospital_name);
            Hospitalservicetype = (TextView) itemView.findViewById(R.id.Hospital_Servicetype);
            HospitalLocation = (TextView) itemView.findViewById(R.id.Hospital_Location);
            HospitalRating = (TextView) itemView.findViewById(R.id.Hospital_Rate);

            HospitalRating.setVisibility(View.GONE);

            // 3 .Set OnClickListener
            itemView.setOnClickListener(this);
        }


        //  4.  Set onClick
        @Override
        public void onClick(View v) {

            this.itemClickListner.onItemClickListner(v, getLayoutPosition());

        }

        ////Set setItemClickListner
        private void setItemClickListner(ItemClickListner itemClickListner) {

            this.itemClickListner = itemClickListner ;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.HospitalName.setText(hospitalList.get(position).getName());
        holder.Hospitalservicetype.setText(hospitalList.get(position).getServicetype());
        holder.HospitalLocation.setText(hospitalList.get(position).getLocation());
        holder.HospitalRating.setText(hospitalList.get(position).getRating());
        Picasso.get().load(hospitalList.get((position)).getUrl())
                .into(holder.imageView);


        // 5. setItemClickListner
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                //6. Set to passing Data
                String gName = hospitalList.get(position).getName();
                String gPhone = hospitalList.get(position).getPhone();
                String gDescription = hospitalList.get(position).getDescription();
                String gLocation = hospitalList.get(position).getLocation();
                String gLatitude = hospitalList.get(position).getLocationlatitude();
                String gLongtitude = hospitalList.get(position).getLocationlongtitude();
                String gServicetype = hospitalList.get(position).getServicetype();
                String gServicetime = hospitalList.get(position).getservicetime();
                String gRating = hospitalList.get(position).getRating();
                String gUrl = hospitalList.get(position).getUrl();

                //7. Put Data to HospitalsDetailActivity
                Intent intent = new Intent(mContext, HospitalsDetailActivity.class);
                intent.putExtra("Name",gName);
                intent.putExtra("Phone",gPhone);
                intent.putExtra("Description",gDescription);
                intent.putExtra("Location",gLocation);
                intent.putExtra("Latitude",gLatitude);
                intent.putExtra("Longtitude",gLongtitude);
                intent.putExtra("Servicetype",gServicetype);
                intent.putExtra("Servicetime",gServicetime);
                intent.putExtra("Rating",gRating);
                intent.putExtra("Url",gUrl);
                mContext.startActivity(intent);


            }

        });


    }
    @Override
    public int getItemCount() {
        return hospitalList.size();
    }


}
