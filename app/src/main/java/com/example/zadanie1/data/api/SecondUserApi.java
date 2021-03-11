package com.example.zadanie1.data.api;

import com.example.zadanie1.model.SecondUser;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SecondUserApi {

    @GET("users")
    Call<List<SecondUser>> getUsers();

    @GET
    public Call<JsonObject> getUser();

}