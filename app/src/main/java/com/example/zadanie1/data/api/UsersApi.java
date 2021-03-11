package com.example.zadanie1.data.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsersApi {

    @GET("users")
    public Call<JsonObject> getUsers();

    @GET
    public Call<JsonObject> getUser();

}
