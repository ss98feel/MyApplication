package com.example.dongkibae.myapplication;

/**
 * Created by dongkibae on 2017. 2. 25..
 */

public class MovieDTO {
    String typeNm;
    String movieNm;
    String movieNmEn;
    String prdtYear;
    String nationAlt;
    String genreAlt;
    String directors;
    String openDt;

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }

    public String getMovieNmEn() {
        return movieNmEn;
    }

    public void setMovieNmEn(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getPrdtYear() {
        return prdtYear;
    }

    public void setPrdtYear(String prdtYear) {
        this.prdtYear = prdtYear;
    }
    public String getNationAlt() {
        return nationAlt;
    }

    public void setNationAlt(String nationAlt) {
        this.nationAlt = nationAlt;
    }

    public String getGenreAlt() {
        return genreAlt;
    }

    public void setGenreAlt(String genreAlt) {
        this.movieNm = genreAlt;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setopenDt(String openDt) {
        this.openDt = openDt;
    }


}
