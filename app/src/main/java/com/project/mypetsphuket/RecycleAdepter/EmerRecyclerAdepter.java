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

import com.project.mypetsphuket.DelailsActivity.EmergencysDetailActivity;
import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.Model.Emergencys;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmerRecyclerAdepter extends RecyclerView.Adapter<EmerRecyclerAdepter.ViewHolder> {

    private static final String TAG = "EmerRecyclerAdepter";
    private Context mContext;
    private ArrayList<Emergencys> emergencysList;


    public EmerRecyclerAdepter(Context context , ArrayList <Emergencys> emergencysList){

        this.mContext = context;
        this.emergencysList = emergencysList;
    }

    @NonNull
    @Override
    public EmerRecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_items_layout,parent,false);
        return new ViewHolder(view);
    }


    //**    1. implements View.OnClickListener  *//
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // 2. Defined parameter
        ItemClickListner itemClickListner;
        ImageView imageView;
        TextView  EmergencyName;
        TextView  EmergencyDescription;
        TextView  Emergencyservicetype;
        TextView  Emergencyservicetime;
        TextView  EmergencyLocation;
        TextView  EmergencyRating;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            //Find Object in RecycleView
            imageView = (ImageView) itemView.findViewById(R.id.Emergency_imageView);
            EmergencyName = (TextView) itemView.findViewById(R.id.Emergency_Name);
            Emergencyservicetype = (TextView) itemView.findViewById(R.id.Emergency_servicetype);
            EmergencyLocation = (TextView) itemView.findViewById(R.id.Emergency_Location);
            EmergencyRating = (TextView) itemView.findViewById(R.id.Emergency_Rating);

            itemView.setOnClickListener(this);
        }

        //  4.  Set onClick
        @Override
        public void onClick(View v) {

            this.itemClickListner.onItemClickListner(v, getLayoutPosition());

        }
        ////Set setItemClickListner
        private void setItemClickListner(ItemClickListner itemClickListner){

            this.itemClickListner = itemClickListner ;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.EmergencyName.setText(emergencysList.get(position).getName());
        holder.Emergencyservicetype.setText(emergencysList.get(position).getServicetype());
        holder.EmergencyLocation.setText(emergencysList.get(position).getLocation());
        holder.EmergencyRating.setText(emergencysList.get(position).getRating());
        Picasso.get().load(emergencysList.get((position)).getUrl())
                .into(holder.imageView);

        // 5. setItemClickListner
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                //6. Set to passing Data
                String gName = emergencysList.get(position).getName();
                String gDescription = emergencysList.get(position).getDescription();
                String gLocation = emergencysList.get(position).getLocation();
                String gServicetype = emergencysList.get(position).getServicetype();
                String gServicetime = emergencysList.get(position).getServicetime();
                String gPhone = emergencysList.get(position).getPhone();
                String gRating = emergencysList.get(position).getRating();
                String gUrl = emergencysList.get(position).getUrl();

                //7. Put Data to HospitalsDetailActivity
                Intent intent = new Intent(mContext, EmergencysDetailActivity.class);
                intent.putExtra("Name",gName);
                intent.putExtra("Description",gDescription);
                intent.putExtra("Location",gLocation);
                intent.putExtra("Servicetype",gServicetype);
                intent.putExtra("Servicetime",gServicetime);
                intent.putExtra("Phone",gPhone);
                intent.putExtra("Rating",gRating);
                intent.putExtra("Url",gUrl);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return emergencysList.size();
    }


}
