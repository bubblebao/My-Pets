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
import com.project.mypetsphuket.DelailsActivity.PetshopsDetailActivity;
import com.project.mypetsphuket.Interface.ItemClickListner;
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

        holder.PetshopName.setText(petshopsList.get(position).getName());
        holder.Petshopservicetype.setText(petshopsList.get(position).getServicetype());
        holder.PetshopLocation.setText(petshopsList.get(position).getLocation());
        holder.PetshopRating.setText(petshopsList.get(position).getRating());
        Picasso.get().load(petshopsList.get((position)).getUrl())
                .into(holder.imageView);

        // 5. setItemClickListner
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                //6. Set to passing Data
                String gName = petshopsList.get(position).getName();
                String gPhone = petshopsList.get(position).getPhone();
                String gDescription = petshopsList.get(position).getDescription();
                String gServicetype = petshopsList.get(position).getServicetype();
                String gServicetime = petshopsList.get(position).getServicetime();
                String gLocation = petshopsList.get(position).getLocation();
                String gRating = petshopsList.get(position).getRating();
                String gUrl = petshopsList.get(position).getUrl();

                //7. Put Data to HospitalsDetailActivity
                Intent intent = new Intent(mContext, PetshopsDetailActivity.class);
                intent.putExtra("Name",gName);
                intent.putExtra("Phone",gPhone);
                intent.putExtra("Description",gDescription);
                intent.putExtra("Location",gLocation);
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
        return petshopsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListner itemClickListner;
        ImageView imageView;
        TextView PetshopName;
        TextView PetshopDescription;
        TextView Petshopservicetype;
        TextView Petshopservicetime;
        TextView PetshopLocation;
        TextView PetshopRating;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.Petshop_imageView);
            PetshopName = (TextView) itemView.findViewById(R.id.Petshop_Name);
            Petshopservicetype = (TextView) itemView.findViewById(R.id.Petshop_Servicetype);
            PetshopLocation = (TextView) itemView.findViewById(R.id.Petshop_location);
            PetshopRating = (TextView) itemView.findViewById(R.id.Petshop_Rating);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListner.onItemClickListner(v, getLayoutPosition());
        }

        private void setItemClickListner(ItemClickListner itemClickListner){

            this.itemClickListner = itemClickListner ;

        }
    }

}
