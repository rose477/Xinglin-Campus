package com.hnucm18jr.roseapp.Shouye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.lbsapi.panoramaview.PanoramaViewListener;
import com.hnucm18jr.roseapp.MyApplication;
import com.hnucm18jr.roseapp.R;

import java.util.Random;

public class KebiaoActivity extends AppCompatActivity {
    /** 第一个无内容的格子 */
    protected TextView empty;
    /** 星期一的格子 */
    protected TextView monColum;
    /** 星期二的格子 */
    protected TextView tueColum;
    /** 星期三的格子 */
    protected TextView wedColum;
    /** 星期四的格子 */
    protected TextView thrusColum;
    /** 星期五的格子 */
    protected TextView friColum;
    /** 星期六的格子 */
    protected TextView satColum;
    /** 星期日的格子 */
    protected TextView sunColum;
    /** 课程表body部分布局 */
    protected RelativeLayout course_table_layout;
    /** 屏幕宽度 **/
    protected int screenWidth;
    /** 课程格子平均宽度 **/
    protected int aveWidth;
    int gridHeight1 = 0;
    //(0)对应12节；(2)对应34节；(4)对应56节；(6)对应78节；(8)对应于9 10节
    int[] jieci = {0,2,3,5,4,6,8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.activity_kebiao);

        ImageView mImageView=findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    //获得列头的控件
    empty = (TextView) this.findViewById(R.id.test_empty);
    monColum = (TextView) this.findViewById(R.id.test_monday_course);
    tueColum = (TextView) this.findViewById(R.id.test_tuesday_course);
    wedColum = (TextView) this.findViewById(R.id.test_wednesday_course);
    thrusColum = (TextView) this.findViewById(R.id.test_thursday_course);
    friColum = (TextView) this.findViewById(R.id.test_friday_course);
    satColum  = (TextView) this.findViewById(R.id.test_saturday_course);
    sunColum = (TextView) this.findViewById(R.id.test_sunday_course);
    course_table_layout = (RelativeLayout) this.findViewById(R.id.test_course_rl);
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    //屏幕宽度
    int width = dm.widthPixels;
    //平均宽度
    int aveWidth = width / 8;
    //第一个空白格子设置为25宽
        empty.setWidth(aveWidth * 3/4);
        monColum.setWidth(aveWidth * 33/32 + 1);
        tueColum.setWidth(aveWidth * 33/32 + 1);
        wedColum.setWidth(aveWidth * 33/32 + 1);
        thrusColum.setWidth(aveWidth * 33/32 + 1);
        friColum.setWidth(aveWidth * 33/32 + 1);
        satColum.setWidth(aveWidth * 33/32 + 1);
        sunColum.setWidth(aveWidth * 33/32 + 1);
        this.screenWidth = width;
        this.aveWidth = aveWidth;
    int height = dm.heightPixels;
    int gridHeight = height / 10;
    gridHeight1 = gridHeight;
    //设置课表界面
    //动态生成10 * maxCourseNum个textview
        for(int i = 1; i <= 12; i ++){

        for(int j = 1; j <= 8; j ++){

            TextView tx = new TextView(KebiaoActivity.this);
            tx.setId((i - 1) * 8  + j);
            //除了最后一列，都使用course_text_view_bg背景（最后一列没有右边框）
            if(j < 8)
                tx.setBackgroundDrawable(KebiaoActivity.this.
                        getResources().getDrawable(R.drawable.course_text_view_bg));
            else
                tx.setBackgroundDrawable(KebiaoActivity.this.
                        getResources().getDrawable(R.drawable.course_text_view_bg));
            //相对布局参数
            RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                    aveWidth * 33 / 32 + 1,
                    gridHeight);
            //文字对齐方式
            tx.setGravity(Gravity.CENTER);
            //字体样式
//                tx.setTextAppearance(this, R.style.courseTableText);
//                //如果是第一列，需要设置课的序号（1 到 12）
            if(j == 1)
            {
                tx.setText(String.valueOf(i));
                rp.width = aveWidth * 3/4;
                //设置他们的相对位置
                if(i == 1)
                    rp.addRule(RelativeLayout.BELOW, empty.getId());
                else
                    rp.addRule(RelativeLayout.BELOW, (i - 1) * 8);
            }
            else
            {
                rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 8  + j - 1);
                rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 8  + j - 1);
                tx.setText("");
            }

            tx.setLayoutParams(rp);
            course_table_layout.addView(tx);
        }
    }

    setCourseMessage(1,jieci[1],"软件工\n程@含\n浦校区\n3508教\n室\n");
    setCourseMessage2(2,jieci[0],"嵌入式\n系统...\n@含浦\n校区\n3204教\n室");
    setCourseMessage(3,jieci[1],"计算机\n图形\n学@含\n浦校区\n3310教\n室");
    setCourseMessage2(4,jieci[0],"计算机\n网络...\n@含浦\n校区\n3311教\n室");
    setCourseMessage2(5,jieci[0],"嵌入式\n系统...\n@h含浦\n校区嵌入\n式实验室");
    setCourseMessage(5,jieci[1],"java...\n@h含浦\n校区12\n机房\n(65)");
    setCourseMessage(1,jieci[3],"人工智\n能原\n理@含\n浦校区\n3306教\n");
    setCourseMessage2(2,jieci[2],"计算机\n网络...\n@含浦\n校区\n2204教\n室");
    setCourseMessage(5,jieci[3],"操作系\n统原\n理@含\n浦校区\n12机房\n(65)");


}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setCourseMessage(int xingqi,int jieci,String courseMessage){
        //五种颜色的背景
        int[] background = {R.drawable.course_info_blue, R.drawable.course_info_green,
                R.drawable.course_info_red, R.drawable.course_info_zisi,
                R.drawable.course_info_yellow};
        // 添加课程信息
        TextView courseInfo = new TextView(this);
        courseInfo.setText(courseMessage);
        //该textview的高度根据其节数的跨度来设置
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                aveWidth * 31 / 32,
                (gridHeight1 - 5) * 3 );
        //textview的位置由课程开始节数和上课的时间（day of week）确定
        rlp.topMargin = 5 + jieci * gridHeight1;
        rlp.leftMargin = 5;
        // 偏移由这节课是星期几决定
        rlp.addRule(RelativeLayout.RIGHT_OF, xingqi);
        //字体剧中
        courseInfo.setGravity(Gravity.CENTER);
        // 设置一种背景
        Random random = new Random();
        courseInfo.setBackgroundResource(background[random.nextInt(5)]);
        courseInfo.setTextSize(12);
        courseInfo.setLayoutParams(rlp);
        courseInfo.setTextColor(Color.WHITE);
        //设置不透明度
        courseInfo.getBackground().setAlpha(222);
        course_table_layout.addView(courseInfo);
    }
    public void setCourseMessage2(int xingqi,int jieci,String courseMessage){
        //五种颜色的背景
        int[] background = {R.drawable.course_info_blue, R.drawable.course_info_green,
                R.drawable.course_info_red, R.drawable.course_info_zisi,
                R.drawable.course_info_yellow};
        // 添加课程信息
        TextView courseInfo = new TextView(this);
        courseInfo.setText(courseMessage);
        //该textview的高度根据其节数的跨度来设置
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                aveWidth * 31 / 32,
                (gridHeight1 - 5) * 2 );
        //textview的位置由课程开始节数和上课的时间（day of week）确定
        rlp.topMargin = 5 + jieci * gridHeight1;
        rlp.leftMargin = 5;
        // 偏移由这节课是星期几决定
        rlp.addRule(RelativeLayout.RIGHT_OF, xingqi);
        //字体剧中
        courseInfo.setGravity(Gravity.CENTER);
        // 设置一种背景
        Random random = new Random();
        courseInfo.setBackgroundResource(background[random.nextInt(5)]);
        courseInfo.setTextSize(12);
        courseInfo.setLayoutParams(rlp);
        courseInfo.setTextColor(Color.WHITE);
        //设置不透明度
        courseInfo.getBackground().setAlpha(222);
        course_table_layout.addView(courseInfo);
    }


}

