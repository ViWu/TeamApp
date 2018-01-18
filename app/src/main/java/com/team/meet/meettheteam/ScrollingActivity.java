package com.team.meet.meettheteam;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public class ScrollingActivity extends AppCompatActivity {

    CustomPageAdapter adapter;
    public static CollapsingToolbarLayout collapsingToolbarLayout;
    static ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ivAvatar = findViewById(R.id.ivAvatar);

        int position = getIntent().getIntExtra("position", 0);
        Log.i("VICTOR", "position clicked scrolling activity: " + position );

        ViewPager pager = (ViewPager) findViewById(R.id.vpPager);
        adapter = new CustomPageAdapter(this, MainActivity.members, position);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);



    }
}
