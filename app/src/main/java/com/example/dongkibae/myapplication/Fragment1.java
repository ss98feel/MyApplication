package com.example.dongkibae.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by dongkibae on 2017. 2. 16..
 */

public class Fragment1 extends Fragment  {

    private String parsinguri = "";
    FunTask funTask;
    RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    private RecyclerView recyclerView;
    BoxOfficeDTO boxOfficeDTO;
    String today = null;
    Date date = new Date();

    public Fragment1()  {

    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                boxOfficeDTO = recyclerAdapter.getItem(position);
                System.out.println("get --" + boxOfficeDTO.getBoxmovieNm());


                Intent intent = new Intent(getActivity(),Detail.class);
                intent.putExtra("texttitle", boxOfficeDTO.getBoxmovieNm());
                intent.putExtra("moviename",boxOfficeDTO.getBoxmovieNm());
                intent.putExtra("openDt",boxOfficeDTO.getBoxopenDt());
                intent.putExtra("audiAcc",boxOfficeDTO.getAudiAcc());
                startActivity(intent);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));




        FunTask funTask=new FunTask();
        funTask.execute();

        return view;
    }


    public class FunTask extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {


            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date);
            c1.add(Calendar.DATE, -1);
            String strToday = sdf.format(c1.getTime());
            System.out.println("하루전 " + strToday);


            HttpURLConnection conn = null;
            parsinguri = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt="+strToday;
            try {
                URL url = new URL(parsinguri); //요청 URL을 입력
                System.out.println("URI + " + url);
                conn = (HttpURLConnection) url.openConnection();
                conn.getInputStream();
                conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
                String line = null;
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }



                JSONObject json = new JSONObject(result);
                JSONObject json2 = json.getJSONObject("boxOfficeResult");
                JSONArray jArr = json2.getJSONArray("dailyBoxOfficeList");


                for(int i =0;i<jArr.length();i++){
                    BoxOfficeDTO bdto = new BoxOfficeDTO();
                    json = jArr.getJSONObject(i);
                    bdto.setBoxmovieNm(json.getString("movieNm"));
                    bdto.setBoxopenDt(json.getString("openDt"));
                    bdto.setAudiAcc(json.getString("audiCnt"));

                    recyclerAdapter.addItem(bdto,1);
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


