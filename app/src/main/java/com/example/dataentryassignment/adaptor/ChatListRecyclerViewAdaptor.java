package com.example.dataentryassignment.adaptor;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dataentryassignment.interfaces.ItemClickListener;
import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatListRecyclerViewAdaptor extends PagedListAdapter< User ,ChatListRecyclerViewAdaptor.holder> {


    Context context;
    ItemClickListener itemClickListener;
    List<User> userArrayList;

    public static DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull User oldItem, @NonNull @NotNull User newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull User oldItem, @NonNull @NotNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    public ChatListRecyclerViewAdaptor(ItemClickListener itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;

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

//        User user = userArrayList.get(position);
        User user = getItem(position);
        holder.textViewName.setText(user.getName());
        holder.textViewNumber1.setText(user.getContactNumber());
        holder.textViewNumber2.setText(user.getContactNumber2());
        holder.textViewNumber3.setText(user.getContactNumber3());

        if (user.getProfilePic()!=null){
            Glide.with(holder.imageViewProfilePic.getContext())
                    .load(Uri.parse(user.getProfilePic()))
                    .error(R.drawable.ic_baseline_person_24)
                    .into(holder.imageViewProfilePic);
        }
        else {
            Glide.with(holder.imageViewProfilePic.getContext())
                    .load(R.drawable.ic_baseline_person_24)
                    .error(R.drawable.ic_baseline_person_24)
                    .into(holder.imageViewProfilePic);
        }

//        User user = getItem(position);
//        holder.textViewName.setText(user.getName());




    }

//    @Override
//    public int getItemCount() {
//        return userArrayList == null ? 0 : userArrayList.size();
//    }

    class holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ImageView imageViewProfilePic;
        TextView textViewName, textViewNumber1, textViewNumber2 ,textViewNumber3 ;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageViewProfilePic = itemView.findViewById(R.id.image_view_profile_pic);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewNumber1 = itemView.findViewById(R.id.text_view_number1);
            textViewNumber2 = itemView.findViewById(R.id.text_view_number2);
            textViewNumber3 = itemView.findViewById(R.id.text_view_number3);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Log.d("TAG","onClick in chatListAdapter"+ v.getId());
            if (itemClickListener!= null)
                itemClickListener.onItemClicked(v,getItem(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            Log.d("TAG","onLongClicked in chatListAdapter"+v.getId());
            if (itemClickListener!=null)
                itemClickListener.onItemLongClicked(v,getItem(getAdapterPosition()),getAdapterPosition());
            return true;
        }
    }
}
