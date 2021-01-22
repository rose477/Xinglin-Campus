package com.hnucm18jr.roseapp.Xuexi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.google.gson.Gson;
import com.hnucm18jr.roseapp.Base64Util;
import com.hnucm18jr.roseapp.FileUtil;
import com.hnucm18jr.roseapp.HttpUtil;
import com.hnucm18jr.roseapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

import static com.hnucm18jr.roseapp.AuthService.getAuth;

public class ShibieActivity extends AppCompatActivity {


    private AipImageClassify aipImageClassify;
    String result="";
    public static final String APP_ID = "23047597";
    public static final String API_KEY = "QqZaOp0TqAfwKiuljhSGN8NC";
    public static final String SECRET_KEY = "dEv0GyimsAh4MTy41bcxn7sdSLw7hS1q";
    ImageView imageView;
    String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";//todo
    // private Bitmap bitmap;


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {//重写handleMessage方法
            super.handleMessage(msg);

            try {
                JSONObject jsonObject=new JSONObject(result);//把请求得到的字符串传进去
                JSONArray jsonArray=jsonObject.getJSONArray("result");
                for (int i=0; i<jsonArray.length();i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String id = jsonObject1.getString("root");

                    String name = jsonObject1.getString("keyword");
                    if (i==0){
                        mTextView.setText("ta是"+id+"中的"+name+"(90%)");
                    }else if (i==1){
                        mTextView2.setText("ta是"+id+"中的"+name+"(80%)");
                    }else {
                        mTextView3.setText("ta是"+id+"中的"+name+"(50%)");
                    }



                }
            }catch (Exception e){

            }


        }
    };
    TextView result1;
    ImageView mImageView;
    TextView mTextView,mTextView2,mTextView3,mTextView5,mTextView6;
    Wuping student;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shibie);
        result1 = findViewById(R.id.textView22);
        final AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        imageView = findViewById(R.id.imageView4);
        mTextView=findViewById(R.id.top11);
        mTextView2=findViewById(R.id.top22);
        mTextView3=findViewById(R.id.top33);

        mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        final Intent intent = getIntent();
        if (intent != null) {
            byte[] bis = intent.getByteArrayExtra("bitmap");
            Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
            imageView.setImageBitmap(bitmap);
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    /**
                     * SDK:
                     */
//                    // 本地文件路径
//                    // 可选：设置网络连接参数
//                    client.setConnectionTimeoutInMillis(2000);
//                    client.setSocketTimeoutInMillis(60000);//
//                    // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//                    //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//                    //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理//
//                    // 调用接口
//                    String path = "/data/data/com.c201801020243.myshcool/cache/image_manager_disk_cache/timg.jpg";
//                    JSONObject res = client.objectDetect(path, new HashMap<String, String>());
//                    System.out.println("结果："+res.toString(3));
                    /**
                     * @API
                     */
                    //String filePath = "/data/data/com.c201801020243.myshcool/cache/image_manager_disk_cache/timg.jpg";
                    String filePath = intent.getStringExtra("filepath");
                    System.out.println("图片路径"+filePath);
                    byte[] imgData = FileUtil.readFileByBytes(filePath);
                    String imgStr = Base64Util.encode(imgData);
                    String imgParam = URLEncoder.encode(imgStr, "UTF-8");

                    String param = "image=" + imgParam;

                    String Token = getAuth(API_KEY, SECRET_KEY);
                    // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
                    String accessToken = Token;//"https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id="+API_KEY+"&client_secret="+SECRET_KEY;

                    result = HttpUtil.post(url, accessToken, param);
                    Log.i("result结果", result);



                    mHandler.sendEmptyMessage(1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        //result1.setText("结果："+result);
    }

}
