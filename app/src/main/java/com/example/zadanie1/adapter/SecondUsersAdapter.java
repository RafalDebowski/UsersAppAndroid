package com.example.zadanie1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zadanie1.R;
import com.example.zadanie1.model.SecondUser;

import java.util.ArrayList;

public class SecondUsersAdapter extends RecyclerView.Adapter<SecondUsersAdapter.ViewHolder> {

    View.OnClickListener listener;

    private String BASE_URL = "https://api.github.com/users";
    private ArrayList<SecondUser> items;

    public SecondUsersAdapter(ArrayList<SecondUser> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.second_list_item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.id.setText(items.get(position).getAvatar_url());
        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getAvatar_url())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.getIcon())
                .getView();
        holder.login.setText(items.get(position).getLogin());
        holder.url.setText(BASE_URL);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClick(View.OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView login, url;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            login = itemView.findViewById(R.id.login);
            url = itemView.findViewById(R.id.api);

            itemView.setOnClickListener(listener);
            itemView.setTag(this);
        }

        public ImageView getIcon() {
            return icon;
        }
    }


}
