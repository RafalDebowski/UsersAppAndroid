package com.example.zadanie1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zadanie1.adapter.SecondUsersAdapter;
import com.example.zadanie1.adapter.UsersAdapter;
import com.example.zadanie1.data.api.UsersApi;
import com.example.zadanie1.database.Database;
import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<SecondUser> secondUsers = new ArrayList<>();


    RecyclerView recyclerView;
    UsersAdapter adapter;
    SecondUsersAdapter secondAdapter;
    RecyclerView secondRecycleView;
    User user;
    SecondUser secondUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        loadUsers();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new UsersAdapter(users);
        recyclerView.setAdapter(adapter);

        Intent detailIntent = new Intent(getContext(), DetailActivity.class);

        adapter.setOnItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
                user = users.get(holder.getAdapterPosition());

                detailIntent.putExtra("id", user.getId());
                startActivity(detailIntent);

            }
        });

        secondRecycleView = view.findViewById(R.id.secondRecycleView);
        secondRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondAdapter = new SecondUsersAdapter(secondUsers);
        secondRecycleView.setAdapter(secondAdapter);

        Intent secondListDetails = new Intent(getContext(), DetailActivity.class);

        secondAdapter.setOnItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
                secondUser = secondUsers.get(holder.getAdapterPosition());

                secondListDetails.putExtra("login", secondUser.getLogin());
                startActivity(secondListDetails);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadUsers() {
        users = (ArrayList<User>) Database.getDatabase(getContext()).userDao().getAllUsers();
        secondUsers = (ArrayList<SecondUser>) Database.getDatabase(getContext()).userDao().getAllSecondUsers();
    }


}