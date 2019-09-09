package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.Images;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdepter extends RecyclerView.Adapter<RecyclerAdepter.ViewHolder> {

    private static final String TAG = "RecyclerAdepter";
    private Context mContext;
    private ArrayList<Images> imagesList;

    public RecyclerAdepter(Context context ,ArrayList <Images> imagesList){

        this.mContext = context;
        this.imagesList= imagesList;
    }

    @NonNull
    @Override
    public RecyclerAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_items_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(imagesList.get(position).getName());
        holder.tvDescription.setText(imagesList.get(position).getDescription());
        holder.tvLocation.setText(imagesList.get(position).getLocation());
        Picasso.get().load(imagesList.get((position)).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvDescription;
        TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvName = (TextView) itemView.findViewById(R.id.Hospital_name);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
