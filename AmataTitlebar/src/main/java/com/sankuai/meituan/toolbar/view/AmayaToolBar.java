package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:13
 */
public class AmayaToolBar extends FrameLayout {
    private Toolbar amayaToolBar;

    public AmayaToolBar(Context context) {
        super(context);
        initView(context);
    }

    public AmayaToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AmayaToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.L)
    public AmayaToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        amayaToolBar = new Toolbar(context);

    }

    public Toolbar getToolBar() {
        return amayaToolBar;
    }

}
