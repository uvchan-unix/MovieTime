package com.zs.movietime.models.DAO.pojo;

public class Movie {
    
    private String moviename;
    private String img;
    private String data;

   

    public Movie(String moviename, String img, String data) {
        this.moviename = moviename;
        this.img = img;
        this.data = data;
    }
    
    public String getMoviename() {
        return moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    
}
