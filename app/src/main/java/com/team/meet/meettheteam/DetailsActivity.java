package com.team.meet.meettheteam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import me.relex.circleindicator.CircleIndicator;

public class DetailsActivity extends AppCompatActivity {

    CustomPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("position", 0);

        ViewPager pager = (ViewPager) findViewById(R.id.vpPager);
        adapter = new CustomPageAdapter(this, MainActivity.members);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        //pager.setPageTransformer(true, new RotateUpTransformer());
    }

}
