package com.hnucm18jr.roseapp.Shouye.canting;

import android.net.Uri;

import cn.bmob.v3.BmobObject;

public class Food1 extends BmobObject {

  public   int bgimage;
  public String name;

  public int getBgimage() {
    return bgimage;
  }

  public void setBgimage(int bgimage) {
    this.bgimage = bgimage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
