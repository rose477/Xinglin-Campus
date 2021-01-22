package com.hnucm18jr.roseapp.Wode;


import java.util.Date;

public class ChatMessager {
    private String messager;// 消息
    private Date date;// 时间
    private Type type;// 类型：发送者.0 接受者.1

    public ChatMessager() {
    }

    public ChatMessager(String messager, Date date, Type type) {
        super();
        this.messager = messager;
        this.date = date;
        this.type = type;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public Date getDate() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        INCOUNT, OUTCOUNT
    }
}
