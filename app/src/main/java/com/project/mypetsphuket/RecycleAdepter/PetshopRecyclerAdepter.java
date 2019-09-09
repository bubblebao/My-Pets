package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.Petshops;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PetshopRecyclerAdepter extends RecyclerView.Adapter<PetshopRecyclerAdepter.ViewHolder> {

    private static final String TAG = "PetshopRecyclerAdepter";
    private Context mContext;
    private ArrayList<Petshops> petshopsList;

    public PetshopRecyclerAdepter(Context context, ArrayList<Petshops> petshopsList) {
        this.mContext = context;
        this.petshopsList = petshopsList;
    }

    @NonNull
    @Override
    public PetshopRecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petshop_items_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetshopRecyclerAdepter.ViewHolder holder, int position) {


        holder.tvName.setText(petshopsList.get(position).getName());
        holder.tvDescription.setText(petshopsList.get(position).getDescription());
        holder.tvLocation.setText(petshopsList.get(position).getLocation());
        holder.tvRating.setText(petshopsList.get(position).getRating());
        Picasso.get().load(petshopsList.get((position)).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return petshopsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvDescription;
        TextView tvLocation;
        TextView tvRating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Petshop_imageView);
            tvName = (TextView) itemView.findViewById(R.id.Petshop_Name);
            tvDescription = (TextView) itemView.findViewById(R.id.Petshop_Description);
            tvLocation = (TextView) itemView.findViewById(R.id.Petshopgency_location);
            tvRating = (TextView) itemView.findViewById(R.id.Petshop_Rating);

        }
    }

}
