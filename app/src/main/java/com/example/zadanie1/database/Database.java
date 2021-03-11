package com.example.zadanie1.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.zadanie1.dao.UserDao;
import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

@androidx.room.Database(entities = {User.class, SecondUser.class}, version = 8, exportSchema = false)
abstract public class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "database.db";
    private static Database INSTANCE;

    public abstract UserDao userDao();

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
