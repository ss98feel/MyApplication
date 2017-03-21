package com.example.dongkibae.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dongkibae on 2017. 2. 14..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{


    ArrayList<BoxOfficeDTO> BoxList = new ArrayList<>();
    ArrayList<MovieDTO> MovieList = new ArrayList<>();
    ArrayList<MoviePersonDTO> MoviePersonList = new ArrayList<>();

    int i=0;

    public Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolder(v);
    }

    public BoxOfficeDTO getItem(int position){


        return BoxList.get(position);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (i==1) {
            BoxOfficeDTO item1 = BoxList.get(position);
            holder.text1.setText(item1.getBoxmovieNm());
            holder.text2.setText(item1.getBoxopenDt());
            holder.text3.setText(item1.getAudiAcc());


        }else if(i==2){
            MovieDTO item2 = MovieList.get(position);
            holder.text1.setText(item2.getMovieNm());
            holder.text2.setText(item2.getPrdtYear());
            holder.text3.setText(item2.getOpenDt());
        }else {
            MoviePersonDTO item3 = MoviePersonList.get(position);
            holder.text1.setText(item3.getPeopleNm());
            holder.text2.setText(item3.getRepRoleNm());
            holder.text3.setText(item3.getPeopleCd());

        }
    }



    @Override
    public int getItemCount() {


        return 10;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        TextView text3;

        public ViewHolder(View itemView) {
            super(itemView);
            text1= (TextView) itemView.findViewById(R.id.text1);
            text2= (TextView) itemView.findViewById(R.id.text2);
            text3= (TextView) itemView.findViewById(R.id.text3);

        }

    }


    public void addItem(BoxOfficeDTO dto,int i) {
        this.i=i;
        BoxOfficeDTO bdto = dto;

        BoxList.add(bdto);
    }
    public void addItem(MovieDTO dto,int i) {
        this.i=i;
        MovieDTO mdto = dto;
        MovieList.add(mdto);
    }
    public void addItem(MoviePersonDTO dto,int i) {
        this.i=i;
        MoviePersonDTO pdto = dto;

        MoviePersonList.add(pdto);
    }


}
