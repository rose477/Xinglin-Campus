package com.hnucm18jr.roseapp.Xuexi;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import cn.bmob.v3.BmobObject;



    @Table(name = "user")
    public class Bianqian extends BmobObject{
        @Column(name = "id", isId = true)
        private int id;

        @Override
        public String toString() {
            return "Bianqian{" +
                    "id=" + id +
                    ", context='" + context + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Column(name = "context")
        private String context;

        @Column(name = "time")
        private String time;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }


