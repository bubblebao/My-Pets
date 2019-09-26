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

import com.project.mypetsphuket.DelailsActivity.DoctorsDetailActivity;
import com.project.mypetsphuket.DelailsActivity.HospitalsDetailActivity;
import com.project.mypetsphuket.Interface.ItemClickListner;
import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DocRecyclerAdepter extends RecyclerView.Adapter<DocRecyclerAdepter.ViewHolder> {

    private static final String TAG = "DocRecyclerAdepter";
    private Context mContext;
    private ArrayList<Doctors> doctorsList;

    public DocRecyclerAdepter(Context context , ArrayList <Doctors> doctorsList){

        this.mContext = context;
        this.doctorsList= doctorsList;
    }

    @NonNull
    @Override
    public DocRecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_items_layout,parent,false);
        return new ViewHolder(view);
    }

                        //**    1. implements View.OnClickListener  *//
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // 2. Defined parameter
        ItemClickListner itemClickListner;
        ImageView imageView;
        TextView DoctorName;
        TextView DoctorSpecialist;
        TextView DoctorLocation;
        TextView DoctorRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Find Object in RecycleView
            imageView = (ImageView) itemView.findViewById(R.id.Doctor_imageView);
            DoctorName = (TextView) itemView.findViewById(R.id.Doctor_Name);
            DoctorSpecialist = (TextView) itemView.findViewById(R.id.Doctor_Description);
            DoctorLocation = (TextView) itemView.findViewById(R.id.Doctor_location);
            DoctorRating = (TextView) itemView.findViewById(R.id.Doctor_Rating);

            // 3. Set OnClickListener
            itemView.setOnClickListener(this);
        }

          //  4.  Set onClick
        @Override
        public void onClick(View v) {
            this.itemClickListner.onItemClickListner(v, getLayoutPosition());
        }
        public void setItemClickListner(ItemClickListner itemClickListner){

            this.itemClickListner = itemClickListner ;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.DoctorName.setText(doctorsList.get(position).getName());
        holder.DoctorSpecialist.setText(doctorsList.get(position).getSpecialist());
        holder.DoctorLocation.setText(doctorsList.get(position).getLocation());
        holder.DoctorRating.setText(doctorsList.get(position).getRating());
        Picasso.get().load(doctorsList.get((position)).getUrl())
                .into(holder.imageView);

         // 5 setItemClickListner
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

         // 6. Set to passing Data
                String gName = doctorsList.get(position).getName();
                String gSpecialist = doctorsList.get(position).getSpecialist();
                String gWorking = doctorsList.get(position).getWorking();
                String gServicetime = doctorsList.get(position).getServicetime();
                String gLocation = doctorsList.get(position).getLocation();
                String gRating = doctorsList.get(position).getRating();
                String gUrl = doctorsList.get(position).getUrl();

           // 7.  Put Data to DoctorsDetailActivity
                Intent intent = new Intent(mContext, DoctorsDetailActivity.class);
                intent.putExtra("Url",gUrl);
                intent.putExtra("Name",gName);
                intent.putExtra("Specialist",gSpecialist);
                intent.putExtra("Working",gWorking);
                intent.putExtra("Servicetime",gServicetime);
                intent.putExtra("Location",gLocation);
                intent.putExtra("Rating",gRating);
                mContext.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return doctorsList.size();
    }
}
