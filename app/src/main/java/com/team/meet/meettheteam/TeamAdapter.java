package com.team.meet.meettheteam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wuv66 on 1/16/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{
    private ArrayList<Member> members;
    private Context context;

    public TeamAdapter(Context context,ArrayList<Member> members) {
        this.members = members;
        this.context = context;
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder viewHolder, int i) {

        Log.i("VICTOR", "member avatar url downloading: " + members.get(i).avatar);

        viewHolder.tvName.setText(members.get(i).firstName + " " +members.get(i).lastName);
        viewHolder.downloadAvatar(members.get(i).avatar);
        //Picasso.with(context).load(members.get(i).avatar).resize(240, 120).into(viewHolder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView ivAvatar;
        public ViewHolder(View view) {
            super(view);

            tvName = (TextView)view.findViewById(R.id.tvName);
            ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        }

        private void downloadAvatar(final String url){

            Picasso.with(context)
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
}