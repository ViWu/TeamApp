package com.team.meet.meettheteam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PageFragment extends Fragment {

    private String avatar, name, title, bio;
    ImageView ivAvatar;

    public static PageFragment newInstance(Member member) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("avatar", member.avatar);
        args.putString("name", member.firstName + " " + member.lastName);
        args.putString("title", member.title);
        args.putString("bio", member.bio);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avatar = getArguments().getString("avatar");
        name = getArguments().getString("name");
        title = getArguments().getString("title");
        bio = getArguments().getString("bio");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_item, container, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(name);

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        TextView tvBio = (TextView) view.findViewById(R.id.tvBio);
        tvBio.setText(bio);

        downloadAvatar(avatar);

        return view;
    }

    private void downloadAvatar(final String url){

        Picasso.with(getContext())
                .load(url)
                //           .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(240, 240)
                .onlyScaleDown()
                .placeholder(R.mipmap.ic_launcher)
                .into(ivAvatar, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //If cache fails, try to fetch from url
                        Picasso.with(getContext())
                                .load(url)
                                .resize(240, 240)
                                .onlyScaleDown()
                                .placeholder(R.mipmap.ic_launcher)
                                //.error(R.drawable.header)
                                .into(ivAvatar, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso","Could not get image");
                                    }
                                });
                    }
                });
    }

}