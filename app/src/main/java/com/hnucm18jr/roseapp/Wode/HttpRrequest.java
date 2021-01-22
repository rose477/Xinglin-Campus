package com.hnucm18jr.roseapp.Wode;


import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class HttpRrequest {
    public static final String URL_KEY = "http://www.tuling123.com/openapi/api";

    public static final String APP_KEY = "0e37575d41dc42818e1c05220c93f817";

    /**
     * 设置参数 返回url
     * */
    private static String grtUrl(String message) {
        String url = "";
        try {
            url = HttpRrequest.URL_KEY + "?" + "key=" + HttpRrequest.APP_KEY
                    + "&info=" + URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
    /**
     * 请求方式：get
     *
     * @return: 返回发送的数据
     * */
    public static String toGet(String message) {
        String result = "";
        String utils = grtUrl(message);
        InputStream is = null;
        ByteArrayOutputStream ba = null;
        try {
            URL urls = new URL(utils);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            // 超时时间
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(5 * 1000);
            connection.setRequestMethod("GET");
            is = connection.getInputStream();
            ba = new ByteArrayOutputStream();
            int len = -1;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                ba.write(buff, 0, len);
            }

            // 确保数据写入
            ba.flush();
            // 将信息传给返回值
            result = new String(ba.toByteArray());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭相应的流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (ba != null) {
                try {
                    ba.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    /**
     * 将获取到的消息数据进行处理
     *
     * @param messager
     * @return 消息
     */
    public static ChatMessager sendMassager(String messager) {
        ChatMessager chatMessager = new ChatMessager();
        String toresult = toGet(messager);
        Gson gs = new Gson();
        IntentCode result = null;
        if (toresult != null) {
            try {
                // Java 对象转成一个将JSON 字符串
                result = gs.fromJson(toresult, IntentCode.class);
                chatMessager.setMessager(result.getMessage());

            } catch (Exception e) {

                chatMessager.setMessager("服务器繁忙，请稍候再试...");
            }
        }
        chatMessager.setData(new Date());
        chatMessager.setType(ChatMessager.Type.INCOUNT);
        return chatMessager;
    }
}


