package com.example.dataentryassignment.adaptor;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatListRecyclerViewAdaptor extends RecyclerView.Adapter<ChatListRecyclerViewAdaptor.holder> {


    Context context;
    List<User> userArrayList;

    public ChatListRecyclerViewAdaptor(Context context) {
        this.context = context;

    }

    public void submitList(List<User> userList){
        this.userArrayList = userList;
        notifyDataSetChanged();


    }


    @NonNull
    @NotNull
    @Override
    public holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_data,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatListRecyclerViewAdaptor.holder holder, int position) {

        User user = userArrayList.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewNumber1.setText(user.getContactNumber());
        holder.textViewNumber2.setText(user.getContactNumber2());
        holder.textViewNumber3.setText(user.getContactNumber3());


//        User user = getItem(position);
//        holder.textViewName.setText(user.getName());




    }

    @Override
    public int getItemCount() {
        return userArrayList == null ? 0 : userArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder {

        ImageView imageViewProfilePic;
        TextView textViewName, textViewNumber1, textViewNumber2 ,textViewNumber3 ;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageViewProfilePic = itemView.findViewById(R.id.image_view_profile_pic);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewNumber1 = itemView.findViewById(R.id.text_view_number1);
            textViewNumber2 = itemView.findViewById(R.id.text_view_number2);
            textViewNumber3 = itemView.findViewById(R.id.text_view_number3);

        }
    }
}
