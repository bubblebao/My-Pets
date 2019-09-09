package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.Doctors;
import com.project.mypetsphuket.Model.Images;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(doctorsList.get(position).getName());
        holder.tvDescription.setText(doctorsList.get(position).getSpecialist());
        holder.tvLocation.setText(doctorsList.get(position).getLocation());
        Picasso.get().load(doctorsList.get((position)).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvDescription;
        TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Doctor_imageView);
            tvName = (TextView) itemView.findViewById(R.id.DocName);
            tvDescription = (TextView) itemView.findViewById(R.id.DocDescription);
            tvLocation = (TextView) itemView.findViewById(R.id.Doctor_location);
        }
    }
}
