package com.example.dongkibae.myapplication;

/**
 * Created by dongkibae on 2017. 2. 14..
 */
public class Item {

    int image;
    String title;

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }

    Item(int image, String title){
        this.image=image;
        this.title=title;
    }

}


