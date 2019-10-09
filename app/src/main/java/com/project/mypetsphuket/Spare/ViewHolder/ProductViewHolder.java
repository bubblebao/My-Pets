package com.project.mypetsphuket.Spare.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Interface.onClick;
import com.project.mypetsphuket.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView txtProductName, txtProductDescription, txtProductPrice;
    private ImageView imageView;
    private onClick listner;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.product_image);
        txtProductName = itemView.findViewById(R.id.product_name);
        txtProductDescription =  itemView.findViewById(R.id.product_description);
        txtProductPrice =  itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListner (onClick listner){
        this.listner = listner;

    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
