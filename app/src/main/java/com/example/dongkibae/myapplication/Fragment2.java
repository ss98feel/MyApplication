package com.example.dongkibae.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongkibae on 2017. 2. 16..
 */

public class Fragment2 extends Fragment {
    private String parsinguri = "";
    Fragment1.FunTask funTask;
    RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    private RecyclerView recyclerView;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);

        FunTask funTask=new FunTask();
        funTask.execute();

        return view;
    }
    public class FunTask extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            HttpURLConnection conn = null;
            parsinguri = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=430156241533f1d058c603178cc3ca0e";
            try {
                URL url = new URL(parsinguri); //요청 URL을 입력
                System.out.println("URI + " + url);
                conn = (HttpURLConnection) url.openConnection();
                System.out.println("conn : " + conn.getInputStream());
                conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
                String line = null;
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }



                JSONObject json = new JSONObject(result);
                JSONObject json2 = json.getJSONObject("movieListResult");
                JSONArray jArr = json2.getJSONArray("movieList");


                for(int i =0;i<jArr.length();i++){
                    MovieDTO mdto = new MovieDTO();
                    json = jArr.getJSONObject(i);
                    mdto.setMovieNm(json.getString("movieNm"));
                    mdto.setPrdtYear(json.getString("prdtYear"));
                    mdto.setopenDt(json.getString("openDt"));
                    recyclerAdapter.addItem(mdto,2);
                }

            } catch (Exception e) {

            }
            return true;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }
}

