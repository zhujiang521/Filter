package com.zj.filters;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ListView;

/**
 * Created by zheng on 2017/11/21.
 */

public class CustomHeightListView extends ListView {

    private Context mContext;

    public CustomHeightListView(Context context) {
        this(context, null);
    }

    public CustomHeightListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            //最大高度显示为屏幕内容高度的一半
            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            //设置控件高度不能超过屏幕高度一半（d.heightPixels / 2，下面有清空按钮所以再减200，也可随意换成自己想要的高度）
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(d.heightPixels / 2 - 200, MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
