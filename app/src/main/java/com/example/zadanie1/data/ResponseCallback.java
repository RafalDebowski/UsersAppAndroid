package com.example.zadanie1.data;

import com.example.zadanie1.model.User;

import java.util.ArrayList;
import java.util.List;

public interface ResponseCallback<T> {
    void onSuccess(ArrayList<User> users);

    void onError(Throwable t);
}
