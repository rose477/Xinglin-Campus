package com.hnucm18jr.roseapp.Xuexi;

import java.util.ArrayList;

public class Wuping {
    public int log_id;
    public int result_num;
    public ArrayList<Mything> result=new ArrayList<>();

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public ArrayList<Mything> getResult() {
        return result;
    }

    public void setResult(ArrayList<Mything> result) {
        this.result = result;
    }

    public class Mything{
        public double score;
        public String root;
        public String keyword;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
