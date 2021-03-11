package com.example.zadanie1.data.controller;

import android.util.Log;

import com.example.zadanie1.ResponseCallback2;
import com.example.zadanie1.data.ResponseCallback;
import com.example.zadanie1.data.api.SecondUserApi;
import com.example.zadanie1.data.api.UsersApi;
import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondUserController {
    public static String TAG = "SecondUsersController";
    public static String BASE_URL = "https://api.github.com/";

    SecondUserApi api;
    private Gson gson = new Gson();

    public SecondUserController() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        api = retrofit.create(SecondUserApi.class);
    }


    public void loadUsers(final ResponseCallback2<SecondUser> callback) {
        Call<List<SecondUser>> call = api.getUsers();
        call.enqueue(new Callback<List<SecondUser>>() {
            @Override
            public void onResponse(Call<List<SecondUser>> call, Response<List<SecondUser>> response) {
                if (response != null) {
                    callback.onSuccess2((ArrayList<SecondUser>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SecondUser>> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.e(TAG, t.getMessage());
                }
                callback.onError2(t);
            }
        });
    }

}
