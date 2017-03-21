package com.example.dongkibae.myapplication;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by dongkibae on 2017. 3. 2..
 */

public class Detail extends AppCompatActivity {
    MovieTask movieTask;
    ImageView iv;
    String te1="",te2="" ,te3="";
    String title;
    TextView t1,t2,t3;
    ArrayList<String> movie = new ArrayList<>();
    ArrayList<String> thumbnail = new ArrayList<>();
    ArrayList<String> genre = new ArrayList<>();
    ArrayList<String> actor = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
        }



        iv= (ImageView) findViewById(R.id.image1);
        t1 = (TextView) findViewById(R.id.text4);
        t2= (TextView) findViewById(R.id.genre);
        t3= (TextView) findViewById(R.id.actor);
        movieTask = new MovieTask();
        movieTask.execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView Title = (TextView)findViewById(R.id.texttitle);
        TextView movieName = (TextView)findViewById(R.id.movieName);
        TextView openDt= (TextView) findViewById(R.id.openDt);
        TextView audiAcc = (TextView) findViewById(R.id.audiAcc);

        Intent intent = getIntent();
        title = intent.getStringExtra("texttitle");
        System.out.println("title --" + title);
        Title.setText(intent.getStringExtra("texttitle"));
        movieName.setText(intent.getStringExtra("moviename"));
        openDt.setText(intent.getStringExtra("openDt"));
        audiAcc.setText(intent.getStringExtra("audiAcc"));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public class MovieTask extends AsyncTask<String,String,Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String clientId = "lcpsYv9rRUwUidD9nKMH";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "lEMK08xc7n";//애플리케이션 클라이언트 시크릿값";
            try {
                String text ="%EC%A3%BC%EC%8B%9D";
                title = URLEncoder.encode(title, "utf-8");
                String apiURL = "https://apis.daum.net/contents/movie?apikey=955b3b958529eb107ee1230423e92153&q="+ title + "&output=json";
                System.out.println("------------------------------------------");
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }

                br.close();
                String a=response.toString();
                JSONObject json = new JSONObject(a);
                JSONObject json2 = json.getJSONObject("channel");
                JSONArray jArr = json2.getJSONArray("item");
                JSONObject json3 = jArr.getJSONObject(0);
                System.out.println("--- "+json3.toString());
                JSONObject json4 = new JSONObject(json3.toString());
                JSONArray jArr2 = json4.getJSONArray("story");
                System.out.println("jArr2 -- "+jArr2.length());
                System.out.println("jarr--" + jArr.length());
                for(int i=0;i<jArr2.length();i++){
                    JSONObject json5 = jArr2.getJSONObject(i);
                    System.out.println("json ---- "+json5.toString());
                    movie.add(i,json5.getString("content"));
                    System.out.println("movie --- "+ movie);
                }

                br.close();
                String aa=response.toString();
                JSONObject json1 = new JSONObject(aa);
                JSONObject json22 = json1.getJSONObject("channel");
                JSONArray jArr1 = json22.getJSONArray("item");
                JSONObject json33 = jArr1.getJSONObject(0);
                JSONObject json44 = new JSONObject(json33.toString());
                JSONArray jArr22 = json44.getJSONArray("thumbnail");

                for(int i=0;i<jArr22.length();i++){
                    JSONObject json55 = jArr22.getJSONObject(i);
                    thumbnail.add(i,json55.getString("content"));
                }

                br.close();
                String aaa=response.toString();
                JSONObject json11 = new JSONObject(aaa);
                JSONObject json222 = json11.getJSONObject("channel");
                JSONArray jArr11 = json222.getJSONArray("item");
                JSONObject json333 = jArr11.getJSONObject(0);
                JSONObject json444 = new JSONObject(json333.toString());
                JSONArray jArr222 = json444.getJSONArray("genre");

                for(int i=0;i<jArr222.length();i++){
                    JSONObject json555 = jArr222.getJSONObject(i);
                    genre.add(i,json555.getString("content"));
                }

                br.close();
                String aaaa=response.toString();
                JSONObject json111 = new JSONObject(aaaa);
                JSONObject json2222 = json111.getJSONObject("channel");
                JSONArray jArr111 = json2222.getJSONArray("item");
                JSONObject json3333 = jArr111.getJSONObject(0);
                JSONObject json4444 = new JSONObject(json3333.toString());
                JSONArray jArr2222 = json4444.getJSONArray("actor");

                for(int i=0;i<jArr2222.length();i++){
                    JSONObject json5555 = jArr2222.getJSONObject(i);
                    actor.add(i,json5555.getString("content"));
                }


//                JSONArray jArr = json2.getJSONArray("item");
                te1=movie.toString();
                te2=genre.toString();
                te3=actor.toString();
                // te1=jArr2.toString();

//                System.out.println("--"+response.toString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e+"-------------");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            t1.setText(te1);
            t2.setText(te2);
            t3.setText(te3);
            Glide.with(getBaseContext()).load(thumbnail.get(0)).into(iv);

        }
    }
}




