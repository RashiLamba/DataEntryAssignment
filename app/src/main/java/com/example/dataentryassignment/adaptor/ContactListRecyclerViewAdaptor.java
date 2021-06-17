package com.example.dataentryassignment.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dataentryassignment.R;
import com.example.dataentryassignment.model.Contact;

import org.jetbrains.annotations.NotNull;

public class ContactListRecyclerViewAdaptor extends PagedListAdapter<Contact, ContactListRecyclerViewAdaptor.holder> {

    public static DiffUtil.ItemCallback<Contact> DIFF_CALLBACK = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Contact oldItem, @NonNull @NotNull Contact newItem) {
            return oldItem.get_id().equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Contact oldItem, @NonNull @NotNull Contact newItem) {
            return oldItem.equals(newItem);
        }
    };

    public ContactListRecyclerViewAdaptor() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @NotNull
    @Override
    public holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_data,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactListRecyclerViewAdaptor.holder holder, int position) {
        Contact contact = getItem(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewNumber.setText(contact.getNumber().get(0));
        holder.textViewNumber2.setText(contact.getNumber().get(0));

        Glide.with(holder.imageViewProfilePic.getContext())
                .load(R.drawable.ic_baseline_person_24)
                .into(holder.imageViewProfilePic);
        

    }

    class holder extends RecyclerView.ViewHolder{
        TextView textViewName,textViewNumber,textViewNumber2,textViewNumber3;
        ImageView imageViewProfilePic;

        public holder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewNumber = itemView.findViewById(R.id.text_view_number1);
            textViewNumber2 = itemView.findViewById(R.id.text_view_number2);
            textViewNumber3 = itemView.findViewById(R.id.text_view_number3);
            imageViewProfilePic = itemView.findViewById(R.id.image_view_profile_pic);

        }
    }

}
