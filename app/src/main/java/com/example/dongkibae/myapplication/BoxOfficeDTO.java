package com.example.dongkibae.myapplication;

/**
 * Created by dongkibae on 2017. 2. 25..
 */

public class BoxOfficeDTO {
    String boxmovieNm;
    String boxopenDt;
    String showRange;
    String rank;
    String boxofficeType;
    String audiAcc;

    public String getBoxmovieNm() {
        return boxmovieNm;
    }

    public void setBoxmovieNm(String boxmovieNm) {
        this.boxmovieNm = boxmovieNm;
    }

    public String getBoxopenDt() {
        return boxopenDt;
    }

    public void setBoxopenDt(String boxopenDt) {
        this.boxopenDt = boxopenDt;
    }

    public String getAudiAcc() {
        return audiAcc;
    }

    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }



    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }


}
