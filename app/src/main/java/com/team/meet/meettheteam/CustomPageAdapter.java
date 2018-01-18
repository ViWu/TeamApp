package com.team.meet.meettheteam;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomPageAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Member> team;
    private String avatar, name, title, bio;
    ImageView ivAvatar;

    //int[] resources = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

    public CustomPageAdapter(Context context, ArrayList<Member> team) {
        context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.team = new ArrayList<Member>(team);
    }

    @Override
    public int getCount() {
        return team.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.page_item, container, false);

        //ImageView ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
        //ivIcon.setImageResource(resources[position]);
        avatar = team.get(position).avatar;
        name = team.get(position).firstName + " " + team.get(position).lastName;
        title = team.get(position).title;
        bio = team.get(position).bio;

        TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvName.setText(name);

        TextView tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        TextView tvBio = (TextView) itemView.findViewById(R.id.tvBio);
        tvBio.setText(bio);

        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        downloadAvatar(avatar);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    private void downloadAvatar(final String url){

        Picasso.with(context)
                .load(url)
                //           .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(640, 640)
                .onlyScaleDown()
                .placeholder(R.mipmap.ic_launcher)
                .into(ivAvatar, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //If cache fails, try to fetch from url
                        Picasso.with(context)
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
