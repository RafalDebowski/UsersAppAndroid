package com.example.zadanie1;

import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

import java.util.ArrayList;
import java.util.List;

public interface ResponseCallback2<T> {
    void onSuccess2(ArrayList<SecondUser> users);

    void onError2(Throwable t);
}
