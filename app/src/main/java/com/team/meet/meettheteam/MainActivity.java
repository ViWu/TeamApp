package com.team.meet.meettheteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Member[] arr;
    public static ArrayList<Member> members;
    public TeamAdapter teamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        members = new ArrayList<>();
        createMemberList();
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvTeam);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        TeamAdapter adapter = new TeamAdapter(getBaseContext(), members);
        recyclerView.setAdapter(adapter);

    }

    //Parse all data from local JSON file, containing all countries + provinces
    public void createMemberList(){
        String json=inputStreamToString(this.getResources().openRawResource(R.raw.team));

        Gson gson = new Gson();
        arr = gson.fromJson(json, Member[].class);

        //Log.i("VICTOR", "member list size: " + arr.length );
        for(int i=0; i < arr.length; i++) {
            members.add(arr[i]);
            //Log.i("VICTOR", "member name: " + arr[i].firstName + " " + arr[i].lastName);
            Log.i("VICTOR", "member name: " + members.get(i).firstName + " " + members.get(i).lastName);
        }
        Log.i("VICTOR", "member list size: " + members.size() );

    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}
