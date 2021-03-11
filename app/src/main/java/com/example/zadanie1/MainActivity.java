package com.example.zadanie1;

import android.os.Bundle;

import com.example.zadanie1.adapter.UsersAdapter;
import com.example.zadanie1.data.ResponseCallback;
import com.example.zadanie1.data.controller.SecondUserController;
import com.example.zadanie1.data.controller.UsersController;
import com.example.zadanie1.database.Database;
import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ResponseCallback<User>, ResponseCallback2<SecondUser> {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<SecondUser> users2 = new ArrayList<>();

    private UsersController controller = new UsersController();
    private SecondUserController secondController = new SecondUserController();
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }


    @Override
    protected void onStart() {
        super.onStart();
        secondController.loadUsers(this);

        controller.loadUsers(this);
    }

    public void refreshlist(ArrayList<User> newUsers) {
        Database.getDatabase(getApplicationContext()).userDao().deleteAll();
        users.clear();
        users.addAll(newUsers);
        Database.getDatabase(getApplicationContext()).userDao().addAll(users);

    }

    private void refreshSecondList(ArrayList<SecondUser> secondUsers) {
        users2.clear();
        users2.addAll(secondUsers);
        Database.getDatabase(getApplicationContext()).userDao().addSecondUsers(users2);
    }


    @Override
    public void onSuccess2(ArrayList<SecondUser> users) {
        refreshSecondList(users);
    }

    @Override
    public void onError2(Throwable t) {
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ArrayList<User> users) {
        refreshlist(users);
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }


}