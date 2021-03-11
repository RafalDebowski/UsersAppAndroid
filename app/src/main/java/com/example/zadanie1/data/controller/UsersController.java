package com.example.zadanie1.data.controller;

import android.util.Log;

import com.example.zadanie1.data.ResponseCallback;
import com.example.zadanie1.data.api.UsersApi;
import com.example.zadanie1.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersController {

    public static String TAG = "UsersController";
    public static String BASE_URL = "https://api.dailymotion.com/";

    UsersApi api;
    private Gson gson = new Gson();

    public UsersController() {


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        api = retrofit.create(UsersApi.class);
    }


    public void loadUsers(final ResponseCallback<User> callback) {
        Call<JsonObject> call = api.getUsers();
        ArrayList<User> users = new ArrayList<>();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response != null) {
                    if (response.body().has("list")) {
                        JsonArray items = response.body().getAsJsonArray("list");
                        for (JsonElement element : items) {
                            users.add(gson.fromJson(element, User.class));
                        }
                    }
                }
                callback.onSuccess(users);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.e(TAG, t.getMessage());
                }
                callback.onError(t);
            }
        });
    }


}

