package com.example.zadanie1;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.zadanie1.database.Database;
import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    String FIRST_URL = "https://api.dailymotion.com/users";
    String SECOND_URL = "https://api.github.com/users";
    String id;
    String name;
    TextView api, login;
    ImageView icon;
    Button back;
    User user;
    SecondUser secondUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.buttonBack);
        api = findViewById(R.id.api);
        login = findViewById(R.id.login);
        icon = findViewById(R.id.icon);

        id = getIntent().getStringExtra("id");

        name = getIntent().getStringExtra("login");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadUsers();

        if (id != null) {
            icon = Glide.with(getApplicationContext())
                    .load("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAhFBMVEX///8AAAD09PT6+vrm5ub39/fu7u7FxcV8fHxRUVHNzc3k5OSZmZmRkZFzc3Ps7Ow7Ozvb29u/v7+xsbFeXl4nJydYWFhkZGSjo6PU1NTLy8uCgoKpqakJCQnc3NyVlZVBQUEgICAuLi6JiYlubm4XFxdJSUklJSU1NTUTExMtLS2vr68Xemd6AAAJmElEQVR4nO2d2XqjOgyAW7JvZG3TbB2Spm3SvP/7nUkZJBkI2CBjcT7+m94QVwZb1mb76amhoaGhoaGhoaGhoaGhoTq68+1+svNDdpP98L3rWiRGhrf17Poc5zrzbxvXojEwXb4m+kZZLT3XIpahs19ndi/kvGy5FrQg3tu3Rv/uXPZ17KP3odm9Xw7j2vXxj0n/fhm7FtmIxY9xB5+ff3quxdbG09Evaaxrold7acIfB+PlYtge3WlvFrfJ6jPtsVp8xqSGCXa9dsqD3e3kK/HsTrzG8eJCB+NRxuPtt0H8eeHWXPuoyjtb5P5kG+vjdV6BnIUZqsIOhlq/el+pP8t/K85QdcxMX20MA+WXS4sylmKriGm2gu/r0EVliM6y9EsaU0VFiRyoGyrhrkADE9rAll2+0rTLf4IFbeKdWb7StF6IdEVd93fSxqc0C44q/DQDRg+PvKcvRukYIM7SsXgH/3ZxVm4uW4Oo0UO5CTQ9idQ2HZYhGtIlbXVYpOPgXF7JICNsbMUgGwvEWOMwRkhzQtzFFk6dD5YGcen/keEtvoFAAVOLaIeLCE95OKi4nFeibSSs+zimbmxtoqcxYWuzMLhScBohOE7drxhjkIUz+oCOivOZiBa3z9ouhFxfXKtTXLzKGjMq+BFdr4lgzqyZG4aZeGZu2BDU69wxQBwcbgOoy0iMGXvTh6hpt2EpGKRv7E1DdsDtMIWhNGVvGkMa7E0bABpvwN926xI17rJiA4xuG+ty394M0OfV5msGbfpqoXFdoizn1YYLAM7+p4XGNYEocGClefCsec0lEyDExmuTRsAc0EvS2QBmip1FGYKw7kxT8JzspIogj+HOgwJ9bmfFgnxk30rzOoATx2/R3AFlyu236APpGDvNg+PiLjI8sNtDsHot2ISaND0sS9ND+/z/ewi61E7sHfIF7nQprId2TGMw7N2th2DT2DGNwbB3Z9OAaWzZLv1jpXkjEfZWmt/bfYE6wDCyE/CDUKU7/xBMY/548B2orTGtAmQk2q91tRF570LrFhrXBdInNhxECMYGFhrXBZx8G8loSJ+7TJKCMrVhV4FN6LKcFpP4/F7+FNp2msqH0mX+yDtkDNyWYYIYAXvTgb2XZwKW2XHngOfQssPV8A6oA+6wt29TiZmA1Uu8HhQWCNixeQ0kOdj5iLuo2YPznV4wmlj3D+DeBjtJn2KycHriWHXsLrMG4JZYPttja+W1FYXUZbMZH7iR3/FSEdJnf+HYovtZeAcNSCbNTvY/2UlqGYOF3ix+Ihn2bg02Alb0fpZfvTq4MejCIBsPZM9Z+YgNOe1F0PE1ZJyWNSPJ/hvnBdAUsjlvVWrN8PneFS/dI5GsRGU2+YIHIXo0gm7/DIp+xRY9fEDQJAyhe/GPxSyR6YW0IXDDOtE2xUzU3oE0IGCvTBJlt7n5Ljbl56J2yCKKjDOzwM1mRn/sLmGYg9LF5w99hePtlF8K/YJ3xoqgR02zsjVWT+QTOQcjlGMR7n3MXxs7sf5J1KKUzXOM9Sark61e/IwheetgnHbi7Kfvj226edJe+If4wwNhlkwarV1c6rvg/cWQxpRGw5sfpDwnegoiwxTRf/l8CUke9RnyI/qIKEqn/6AL2dTkA4aMLvkdiuE7D24bMBwPjsY9nPUXrjfE6tFZ+Pm9ecTXXkCIOxNvcc7vRk4n3wR3ct5PPe3RmFehw3Vprlwecv2QcFqEwnScL3Yo+3fCjEnHF2W9jbLWv8vZ3y2Xm/m8fafb+f0zmm96y7F/TlilhFfXO/GB0UPledotpt3sOeV1N2/JU0z/8SXipCjvgfYMxgafYH5bpw/egfOx6k3S5Dr5PXNNsfmT+i3PbheP5TEp0stHpkeYRTt1wE7crR2jFD1xLlnPOxonh+uLq+mYHKAnltPje6tEw06OwX6fxcVYsb3qUT/xIauvwEys8D7rcZzdxKn1/WpnYyc+A312jdeJv8NjlSeaxsMUaysavRM/+7y6CrfY29U807oA7Zg1ca5opMbuBbCqA4bqJSA/VcQauyflf1rPocRUjv2lcajo8VMFZmPMrrA9GdW8RL+aKvpYSsvq/7pVNwMpQyU6YrPWTXmZVV7T0FK026s1lapM+orTtMoKFVjqotLByuvLh/a7qAxRB3sep/T+oZmFLtIOvjip3G1Rr6pwVdJD6BANXGUxaciLe9sqnehf7qIK1Bbn3fBFbxBxWjJI3zRnGf+StOv4nHRaW8a3YFFF7fL8u1/ocOIqsR0RY1vApRP0K/LYjfRWDedf8A7tIktpA3FeBHzBO/S6EIYwI6mOObk/PTyEpLrKjyqiRhn2UnBBrJuyJSpk/4qo+4lIPVW5LGOLBLZF5PIiuugTlxtaZBKK2YIUglu9S9k2pP5exjY5AlEQxX3VzhEaseGPlYSMr8LjlChliVU8mE0tumSQ6wxFXtZH7rIrWDiNVaDiJmEImYqFfHIMWxy5ReNiXeobkN297o7ayoFcU1QgvYCGkd0weikwyWC+oRbVzLcUezsN/A7GVzGhKhZTXZYG2s2mF5jh55e1VzUBRt8MzUqML0vf4QEhFrOLS3GlEaxmQlBUoxMyodzXynGBvGBpssGPcBaK2hKfDjpABjMRgk9XyStFBKh9fdtrXqdPSEectoOAKR6bgvEBil8349Ct1yek6lQzWIYxZfmKNMR0bYMwltg943Eg56Z3qxDu5JUUIM0E55WWEQ16RkQaRg+oYdQ5lBoDbCKDM+lghlPDw+iZPCwFvFZaY5jCIK2NnrkDTlR+ALwDJavO9+OYAGbYMdfOBE0qNsCWDmiP3LAZ5FfFO4YqE225rZ6ybhEYe3mZeDigO388CwOWgBxTEzwRx/cMmwNrQM4yDtOQ7yb4igAHIyexL+FKiWKAbRpkPxc9JufUSV2w5CDzMdBIEo7vNQQmYmadlOWrcayy15IdcuOiSkv0AMMt06CG01HFHdqQzzSK75+yntKbrULRER5y/0KKEM2AnHdG9AUOIa2Z2R0C8agMJQL+vbASLz1gIciwxyBSWqMQDQKLeYYyhVhAbc5No+hcrgtmQd1cpxANPQmVDTUKsxGiBfHyWPzIwdcLjosjSiReH9YetKJ9B5lWgVxgQXzo+kERVY3i+RSwqh8u+XXvISx2D5cC6GGtwt0I9PChUVP3HoKH+LCgFnoo+LTpLCAY9TjuPch9QjRRsDcj1vserpm19Czu/LO9szJso79O/kX4edpZbP6OwllOPsLz6mmxRXg1jL80NDQ0NDQ0NDQ8Pf0HAYxfQoUqO2wAAAAASUVORK5CYII=")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(icon)
                    .getView();


            login.setText(user.getScreenname());
            api.setText(FIRST_URL);
        } else {
            String url = secondUser.getAvatar_url();
            icon = Glide.with(getApplicationContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(icon)
                    .getView();

            login.setText(secondUser.getLogin());
            api.setText(SECOND_URL);
        }

    }

    private void loadUsers() {
        user = Database.getDatabase(getApplicationContext()).userDao().getUser(id);
        secondUser = Database.getDatabase(getApplicationContext()).userDao().getSecondUser(name);
    }

    private void loadImage(ImageView view) {
        String url = secondUser.getAvatar_url();

        Glide.with(getApplicationContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(icon);

    }
}