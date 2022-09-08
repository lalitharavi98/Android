package com.learningjavaandroid.contactroom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learningjavaandroid.contactroom.R;
import com.learningjavaandroid.contactroom.model.Contact;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Contact> contactList;
    private Context context;
    private OnContactClickListener onContactClickListener;

    public RecyclerViewAdapter(List<Contact> contactList, Context context, OnContactClickListener onContactClickListener) {
        this.contactList = contactList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    /*
             The onCreateViewHolder method returns the inflated view which represents a
              single row for the recyclerview.
              LayoutInflater - XML to view object
        */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = Objects.requireNonNull(contactList.get(position));
        holder.name.setText(contact.getName());
        holder.occupation.setText((contact.getOccupation()));

    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(contactList).size();
    }

    //Interaction with view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView occupation;
        public OnContactClickListener onContactClickListener;
        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            // findByViewId cant be used directly as we r not inside the activity.
            name = itemView.findViewById(R.id.row_name_textview);
            occupation = itemView.findViewById((R.id.row_occupation_textview));
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "onClick: clicked");
            this.onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener {
        void onContactClick(int position);
    }
}
