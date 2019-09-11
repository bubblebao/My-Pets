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
import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.Model.Hospitals;
import com.project.mypetsphuket.Model.Images;
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


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Defined parameter
        ItemClickListner itemClickListner;
        ImageView imageView;
        TextView HospitalName;
        TextView HospitalDescription;
        TextView HospitalLocation;
        TextView HospitalRating;

        public ViewHolder(@NonNull View itemView) {

            //Find Object in RecycleView
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Hospital_ImageView);
            HospitalName = (TextView) itemView.findViewById(R.id.Hospital_name);
            HospitalDescription = (TextView) itemView.findViewById(R.id.Hospital_Description);
            HospitalLocation = (TextView) itemView.findViewById(R.id.Hospital_Location);
            HospitalRating = (TextView) itemView.findViewById(R.id.Hospital_Rate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            this.itemClickListner.onItemClickListner(v, getLayoutPosition());

        }

        public void setItemClickListner(ItemClickListner itemClickListner) {

            this.itemClickListner = itemClickListner ;
        }
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.HospitalName.setText(hospitalList.get(position).getName());
        holder.HospitalDescription.setText(hospitalList.get(position).getDescription());
        holder.HospitalLocation.setText(hospitalList.get(position).getLocation());
        holder.HospitalRating.setText(hospitalList.get(position).getRating());
        Picasso.get().load(hospitalList.get((position)).getUrl())
                .into(holder.imageView);

        holder.setItemClickListner(new ItemClickListner() {

            @Override
            public void onItemClickListner(View v, int position) {

                //Insert Data from Hospitals
                String gName = hospitalList.get(position).getName();
                String gDescription = hospitalList.get(position).getDescription();
                String gLocation = hospitalList.get(position).getLocation();
                String gRating = hospitalList.get(position).getRating();
                String gUrl = hospitalList.get(position).getUrl();

                //Put Data to HospitalsDetailActivity
                Intent intent = new Intent(mContext, HospitalsDetailActivity.class);
                intent.putExtra("Name",gName);
                intent.putExtra("Description",gDescription);
                intent.putExtra("Location",gLocation);
                intent.putExtra("Rating",gRating);
                intent.putExtra("Url",gUrl);
                mContext.startActivity(intent);

            }

        });

  /*      holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                    if (hospitalList.get(position).getName().equals("RTB")){


                    }if (hospitalList.get(position).getName().equals("Siriroth")){


                    }
                    if (hospitalList.get(position).getName().equals("ISSO")){


                    }
                    if (hospitalList.get(position).getName().equals("Akane")){


                    }

            }
        });   */

    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }



}
