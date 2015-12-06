package com.esoxjem.moviesguide.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esoxjem.moviesguide.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            MoviesFragment moviesFragment = MoviesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, moviesFragment).commit();
        }
    }

}
