package com.hnucm18jr.roseapp.Shouye.ershou;

import cn.bmob.v3.BmobObject;

public class Goods2  extends BmobObject {
    public String title;
    public String price;
    public String name;
    public String time;

    @Override
    public String toString() {
        return "Goods2{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
