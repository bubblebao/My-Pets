package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(emergencysList.get(position).getName());
        holder.tvDescription.setText(emergencysList.get(position).getDescription());
        holder.tvLocation.setText(emergencysList.get(position).getLocation());
        holder.tvRating.setText(emergencysList.get(position).getRating());
        Picasso.get().load(emergencysList.get((position)).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return emergencysList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvDescription;
        TextView tvLocation;
        TextView tvRating;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Emergency_imageView);
            tvName = (TextView) itemView.findViewById(R.id.Emer_Name);
            tvDescription = (TextView) itemView.findViewById(R.id.Emer_Description);
            tvLocation = (TextView) itemView.findViewById(R.id.Emergency_location);
            tvRating = (TextView) itemView.findViewById(R.id.Emer_Rating);
        }
    }
}
