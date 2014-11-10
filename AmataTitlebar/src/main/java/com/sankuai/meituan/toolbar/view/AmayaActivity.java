package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public abstract class AmayaActivity extends ActionBarActivity {

    private AmayaToolBar amayaToolBar;
    private boolean showProgressing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaya);
    }

    /**
     * 系统默认方法
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID,false);
    }

    /**
     * 系统默认方法
     * @param view
     */
    @Override
    public void setContentView(View view) {
        setContentView(view,false);
    }


    /**
     * 自定义ToolBar方法
     * @param resId             子view的资源id
     * @param overlayTitle      true:覆盖子view；false:上下布局
     */
    public void setContentView(int resId,boolean overlayTitle){
        View childView = LayoutInflater.from(this).inflate(resId, null);
        setContentView(childView,overlayTitle);
    }
    public void setContentView(View childView,boolean overlayTitle){
        generateAmayaTitleBar();
        if(overlayTitle){
            FrameLayout parent = new FrameLayout(this);
            parent.addView(childView);
            int dimension = (int) getResources().getDimension(R.dimen.amaya_title_bar_height);
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,dimension);
            flp.gravity = Gravity.TOP;
            parent.addView(amayaToolBar,flp);
            super.setContentView(parent);
        }else{
            LinearLayout parent = new LinearLayout(this);
            parent.setOrientation(LinearLayout.VERTICAL);
            parent.addView(amayaToolBar);
            LinearLayout.LayoutParams flp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            parent.addView(childView,flp);
            super.setContentView(parent);
        }
        initToolBar();
    }

    /**
     * 将自定义的Toolbar加载到系统
     */
    private void initToolBar() {
        setSupportActionBar(amayaToolBar.getToolBar());
    }

    /**
     * 生成自定义Toolbar复合布局
     * @return
     */
    private AmayaToolBar generateAmayaTitleBar() {
        amayaToolBar = new AmayaToolBar(this);
        return amayaToolBar;
    }


    /**
     * 设置Toolbar背景
     * @param res 背景资源
     */
    public void setActionBarBackgroundResource(int res) {
        amayaToolBar.setBackgroundResource(res);
    }

    /**
     * 设置Toolbar背景
     * @param color 背景资源
     */
    public void setActionBarBackgroundColor(int color) {
        amayaToolBar.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * 设置Toolbar背景
     * @param color  like #112233
     */
    public void setActionBarBackgroundColorStr(String color) {
        amayaToolBar.setBackgroundColor(Color.parseColor(color));
    }
    /**
     * 设置Toolbar背景
     * @param drawable  背景资源
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setActionBarBackground(Drawable drawable) {
        amayaToolBar.setBackground(drawable);
    }

    /**
     * 在右上角显示缓冲提示框
     * @param show      true:显示；false:隐藏
     */
    public void showLoading(boolean show){
        amayaToolBar.showLoading(show);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean supportAPI(int needApi){
        return Build.VERSION.SDK_INT >= needApi;
    }
}
