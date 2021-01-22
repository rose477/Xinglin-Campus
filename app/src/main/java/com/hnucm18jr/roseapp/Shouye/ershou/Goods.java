package com.hnucm18jr.roseapp.Shouye.ershou;

import cn.bmob.v3.BmobObject;

public class Goods extends BmobObject {
    public int image;
    public String title;

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String user;
    public String time;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public  String price;
}
