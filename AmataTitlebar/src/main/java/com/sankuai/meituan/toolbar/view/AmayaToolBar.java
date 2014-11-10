package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:13
 */
public class AmayaToolBar extends FrameLayout {
    private Toolbar amayaToolBar;
    private EditText amayaSearchView;
    private AmayaProgressBar amayaLoading;

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
        addView(amayaToolBar);
        amayaSearchView = new EditText(context);
        addView(amayaSearchView);
        showSearchView(false);
        amayaLoading = new AmayaProgressBar(context, null);
        FrameLayout.LayoutParams flp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        flp.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        flp.rightMargin = 3;
        addView(amayaLoading,flp);

    }

    private void showSearchView(boolean show) {
        amayaSearchView.setVisibility(show?VISIBLE:GONE);
    }


    public Toolbar getToolBar() {
        return amayaToolBar;
    }

    public void showLoading(boolean show) {
        if(show && isShowLoading()) return;
        amayaLoading.setVisibility(show?VISIBLE:GONE);
        amayaLoading.reset();
    }

    private boolean isShowLoading() {
        return amayaLoading.getVisibility() ==VISIBLE;
    }
}
