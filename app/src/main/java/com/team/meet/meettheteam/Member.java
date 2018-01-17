package com.team.meet.meettheteam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by wuv66 on 1/16/2018.
 */

public class Member {

        @SerializedName("avatar")
        public String avatar;

        @SerializedName("bio")
        public String bio;

        @SerializedName("firstName")
        public String firstName;

        @SerializedName("id")
        public String id;

        @SerializedName("lastName")
        public String lastName;

        @SerializedName("title")
        public String title;

}
