package com.project.mypetsphuket.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.mypetsphuket.Spare.Profile;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdepter extends RecyclerView.Adapter<MyAdepter.MyViewHolder> {

    Context context;
    ArrayList<Profile> profiles;

    public MyAdepter(Context c , ArrayList<Profile> p)
    {
        context = c;
        profiles = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.email.setText(profiles.get(position).getEmail());
        Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);
      /*  if(profiles.get(position).isPermission()) {

        }    */
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView profilePic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);

        }

    }
}
