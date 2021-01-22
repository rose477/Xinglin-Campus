package com.hnucm18jr.roseapp.Xuexi;

import cn.bmob.v3.BmobObject;

public class Suoping extends BmobObject {
    public String name;
    public String time;

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
