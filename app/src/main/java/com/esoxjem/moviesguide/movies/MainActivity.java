package com.esoxjem.moviesguide.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.esoxjem.moviesguide.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        if(savedInstanceState == null)
        {
            MoviesFragment moviesFragment = MoviesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, moviesFragment).commit();
        }
    }

    private void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Popular Movies");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

}
