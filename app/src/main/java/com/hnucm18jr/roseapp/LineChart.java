package com.hnucm18jr.roseapp;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;

public class LineChart extends View {
    private Paint XPaint;
    private Paint YPaint;
    private Paint pointPaint;
    private Paint circlePaint;
    private Paint shapePaint;
    private Paint effectPaint, effectPaint1;
    private float yandianx, yuandiany, height, wigth;
    private Context context;
    private String ysplit[];
    private String unit;
    private float max;
    private int textSize = 7;
    private int margin = 20;
    private float gao;
    private boolean start = false;
    private Map<String, Float> mapx;

    public LineChart(Context context) {
        super(context);
        this.context = context;
    }


    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LineChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    private void initView() {
        XPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        XPaint.setAntiAlias(true);
        XPaint.setColor(Color.parseColor("#1e90ff"));
        XPaint.setStrokeWidth(dp2px(1));

        YPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        YPaint.setAntiAlias(true);
        YPaint.setColor(Color.BLACK);
        YPaint.setStrokeWidth(dp2px(2));

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(dp2px(1));

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(1);


        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shapePaint.setAntiAlias(true);
        shapePaint.setColor(Color.TRANSPARENT);
        shapePaint.setStyle(Paint.Style.FILL);
        shapePaint.setStrokeWidth(1);

        effectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        effectPaint.setAntiAlias(true);
        effectPaint.setColor(Color.BLACK);
        effectPaint.setStyle(Paint.Style.FILL);
        effectPaint.setStrokeWidth(1);
        effectPaint.setTextSize(sp2px(textSize));
        effectPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        effectPaint1.setAntiAlias(true);
        effectPaint1.setColor(Color.BLACK);
        effectPaint1.setStyle(Paint.Style.FILL);
        effectPaint1.setStrokeWidth(1);
        effectPaint1.setTextSize(sp2px(9));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (start) {
            yandianx = dp2px(margin);
            yuandiany = getMeasuredHeight() - dp2px(margin);
            wigth = getMeasuredWidth() - dp2px(margin * 2);
            height = getMeasuredHeight() - dp2px(margin * 2);
            float point[] = new float[]{yandianx, yuandiany, yandianx, yandianx, yandianx - dp2px(3), yandianx + dp2px(textSize / 2)};
            canvas.drawLines(point, 0, 4, YPaint);
            canvas.drawLines(point, 2, 4, YPaint);
            canvas.drawLine(yandianx, yandianx, yandianx + dp2px(3), yandianx + dp2px(textSize / 2), YPaint);
            canvas.drawLine(yandianx, yuandiany, yandianx + wigth, yuandiany, YPaint);
            canvas.drawLine(yandianx + wigth, yuandiany, yandianx + wigth - dp2px(textSize / 2), yuandiany - dp2px(3), YPaint);
            canvas.drawLine(yandianx + wigth, yuandiany, yandianx + wigth - dp2px(textSize / 2), yuandiany + dp2px(3), YPaint);
            canvas.drawText("0", yandianx - sp2px(textSize) - dp2px(2), yuandiany + sp2px(textSize) + dp2px(2), effectPaint);
            if (null != unit) {
                canvas.drawText(unit, yandianx - sp2px(textSize) * unit.length() / 2, yuandiany - height - dp2px(3), effectPaint);
            }
            if (null != ysplit && ysplit.length > 0) {
                gao = height / (ysplit.length + 1);
                for (int i = 0; i < ysplit.length; i++) {
                    float a = Float.parseFloat(ysplit[i]);
                    if (max < a) {
                        max = a;
                    }
                    canvas.drawLine(yandianx, yuandiany - (i + 1) * gao, yandianx + dp2px(3), yuandiany - (i + 1) * gao, YPaint);
                    canvas.drawText(ysplit[i], yandianx - (sp2px(textSize) * (ysplit[i].length())), yuandiany - (i + 1) * gao + sp2px(textSize / 2), effectPaint);
                }
            }
            if (mapx.size() > 0) {
                float kuan = wigth / (mapx.size() + 1);
                Object o[] = mapx.keySet().toArray();
                for (int i = 0; i < o.length; i++) {
                    String s = o[i].toString();
                    float x = yandianx + (i + 1) * kuan;
                    float y = yuandiany - (height - gao) / max * mapx.get(o[i]);
                    canvas.drawLine(x, yuandiany, x, yuandiany - dp2px(3), YPaint);
                    canvas.drawText(s, x - (sp2px(textSize) * (s.length() / 2)), yuandiany + sp2px(textSize) + dp2px(3), effectPaint);
                    if (i > 0) {
                        canvas.drawLine(yandianx + i * kuan, yuandiany - (height - gao) / max * mapx.get(o[i - 1]), x, y, XPaint);
                    }
                    int size = dp2px(3);
                    for (int i1 = 0; i1 < (x - yandianx) / size; i1++) {
                        if (i1 % 2 == 0)
                            canvas.drawLine(i1 * size + yandianx, y, (i1 + 1) * size + yandianx, y, shapePaint);
                    }
                    for (int i1 = 0; i1 < (yuandiany - y) / size; i1++) {
                        if (i1 % 2 == 0)
                            canvas.drawLine(x, yuandiany - i1 * size, x, yuandiany - (i1 + 1) * size, shapePaint);
                    }

                    String text = mapx.get(o[i]).toString();
                    canvas.drawText(text, x - text.length() * sp2px(textSize / 4), y - dp2px(3), effectPaint1);
                }
                for (int i = 0; i < o.length; i++) {
                    float x = yandianx + (i + 1) * kuan;
                    float y = yuandiany - (height - gao) / max * mapx.get(o[i]);
                    canvas.drawCircle(x, y, dp2px(3), circlePaint);
                    canvas.drawCircle(x, y, dp2px(3), pointPaint);
                }
            }
        }
    }


    public void startDraw(Map<String, Float> mapx, String[] ysplit, String unit, int margin, int textSize) {
        start = true;
        this.mapx = mapx;
        this.ysplit = ysplit;
        this.unit = unit;
        this.textSize = textSize;
        this.margin = margin;
        initView();
        invalidate();
    }

    /**
     * sp转换成px
     */
    private int sp2px(float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * dp转换成px
     */
    private int dp2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
