package com.hnucm18jr.roseapp.Shouye.canting;

import cn.bmob.v3.BmobObject;

public class Cai extends BmobObject {
    public int image;
    public String name;
    public int price;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
