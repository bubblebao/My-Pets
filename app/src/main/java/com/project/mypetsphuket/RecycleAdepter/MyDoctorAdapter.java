package com.project.mypetsphuket.RecycleAdepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mypetsphuket.Model.BookingDoctor;
import com.project.mypetsphuket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyDoctorAdapter extends RecyclerView.Adapter<MyDoctorAdapter.MyViewHolder> {

    Context context;
    List<BookingDoctor> bookingDoctorList;

    public MyDoctorAdapter(Context context, List<BookingDoctor> bookingDoctorList) {
        this.context = context;
        this.bookingDoctorList = bookingDoctorList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(context)
               .inflate(R.layout.book_doctor_layout ,parent,false );
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_doctor_name.setText(bookingDoctorList.get(position).getName());
        holder.doctor_RatingBar.setRating((float)bookingDoctorList.get(position).getRating());
        Picasso.get().load(bookingDoctorList.get(position).getUrl()).into(holder.doctor_book_ImagView);

    }

    @Override
    public int getItemCount() {
        return bookingDoctorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_doctor_name ;
        ImageView doctor_book_ImagView;
        RatingBar doctor_RatingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doctor_book_ImagView = (ImageView) itemView.findViewById(R.id.book_doctor_imageView);
            txt_doctor_name = (TextView) itemView.findViewById(R.id.txt_book_doctor_name);
            doctor_RatingBar = (RatingBar) itemView.findViewById(R.id.rtb_booking_doctor);

        }
    }
}
 /*   Context context;
    List<BookingDoctor> bookingDoctorList;

    public MyDoctorAdapter(Context context, List<BookingDoctor> bookingDoctorList) {
        this.context = context;
        this.bookingDoctorList = bookingDoctorList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_doctor_layout, parent ,false );
        return new MyDoctorAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_doctor_name.setText(bookingDoctorList.get(position).getName());
        holder.doctor_RatingBar.setRating((float)bookingDoctorList.get(position).getRating());
        Picasso.get().load(bookingDoctorList.get((position)).getUrl()).into(holder.book_ImagView);

    }

    @Override
    public int getItemCount() {
        return bookingDoctorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_doctor_name , txt_item_address;
        ImageView book_ImagView;
        RatingBar doctor_RatingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            txt_doctor_name =  (TextView) itemView.findViewById(R.id.txt_book_doctor_name);
            book_ImagView = (ImageView) itemView.findViewById(R.id.Book_doctor_imageView);
            doctor_RatingBar = itemView.findViewById(R.id.rtb_booking_doctor);
        }
    }
}
*/