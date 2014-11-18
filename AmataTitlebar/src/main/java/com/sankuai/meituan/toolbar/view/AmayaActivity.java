package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.sankuai.meituan.bean.AmayaItem;

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
        int dimension = (int) getResources().getDimension(R.dimen.amaya_title_bar_height);
        if(overlayTitle){
            FrameLayout parent = new FrameLayout(this);
            parent.addView(childView);
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,dimension);
            flp.gravity = Gravity.TOP;
            parent.addView(amayaToolBar,flp);
            super.setContentView(parent);
        }else{
            LinearLayout parent = new LinearLayout(this);
            parent.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams flp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dimension);
            parent.addView(amayaToolBar, flp2);
            LinearLayout.LayoutParams flp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            parent.addView(childView,flp);
            super.setContentView(parent);
        }

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
    public void setMTActionBarBackgroundResource(int res) {
        amayaToolBar.setBackgroundResource(res);
    }

    /**
     * 设置Toolbar背景
     * @param color 背景资源
     */
    public void setMTActionBarBackgroundColor(int color) {
        amayaToolBar.setBackgroundColor(color);
    }

    /**
     * 设置Toolbar背景
     * @param color  like #112233
     */
    public void setMTActionBarBackgroundColorStr(String color) {
        amayaToolBar.setBackgroundColor(Color.parseColor(color));
    }
    /**
     * 设置Toolbar背景
     * @param drawable  背景资源
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setMTActionBarBackground(Drawable drawable) {
        amayaToolBar.setBackground(drawable);
    }

    /**
     * 在右上角显示缓冲提示框
     * @param show      true:显示；false:隐藏
     */
    public void showMTLoading(boolean show) {
        amayaToolBar.showLoading(show);
    }

    public boolean supportAPI(int needApi){
        return Build.VERSION.SDK_INT >= needApi;
    }

    public void addMTItem(AmayaItem item) {
        amayaToolBar.addItem(item, 0);
    }

    /**
     * 增加类似标题ActionBar右侧的MenuItem
     *
     * @param item         Item封装对象
     * @param replaceIndex 位置，从左往右0->1->2,传0
     */
    public void addMTItem(AmayaItem item, int replaceIndex) {
        amayaToolBar.addItem(item, replaceIndex);
    }


    /**
     * 设置标题栏背景
     *
     * @param drawable 资源id
     */
    public void setMTActionbarBackground(int drawable) {
        amayaToolBar.setBackgroundResource(drawable);
    }

    /**
     * 设置Logo
     *
     * @param drawable 资源ID
     */
    public void setMTLogo(int drawable) {
        amayaToolBar.setLogo(drawable);
    }

    /**
     * 显示/隐藏 Logo图标
     *
     * @param visible true:显示；falsely:隐藏
     */
    public void setMTLogoVisible(boolean visible) {
        amayaToolBar.setLogoVisible(visible);
    }


    /**
     * 显示/隐藏 返回小图标
     *
     * @param visible true:显示；falsely:隐藏
     */
    public AmayaToolBar setMTBackViewVisible(boolean visible) {
        amayaToolBar.setBackViewVisible(visible);
        return amayaToolBar;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public AmayaToolBar setMTTitle(int title) {
        amayaToolBar.setTitle(title);
        return amayaToolBar;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public AmayaToolBar setMTTitle(String title) {
        amayaToolBar.setTitle(title);
        return amayaToolBar;
    }

    /**
     * 设置标题颜色
     *
     * @param color
     * @return
     */
    public AmayaToolBar setMTTitleColor(int color) {
        amayaToolBar.setTitleColor(color);
        return amayaToolBar;
    }

    /**
     * 增加Menu回调接口
     *
     * @param listener
     * @return
     */
    public AmayaToolBar addMTItemClick(AmayaMenuClickListener listener) {
        amayaToolBar.addAmayaItemClick(listener);
        return amayaToolBar;
    }

    /**
     * 清除MenuItem
     *
     * @return
     */
    public AmayaToolBar clearMTItem() {
        amayaToolBar.clearItem();
        return amayaToolBar;
    }

    /**
     * 设置MenuItem背景资源
     *
     * @param drawable
     * @return
     */
    public AmayaToolBar setMTItemBackdround(int drawable) {
        amayaToolBar.setItemSelector(drawable);
        return amayaToolBar;
    }

    /**
     * 设置更多MenuItem弹出菜单的背景资源
     *
     * @param drawable
     * @return
     */
    public AmayaToolBar setMTPopWindowBg(int drawable) {
        amayaToolBar.setPopWindowBg(drawable);
        return amayaToolBar;
    }

    /**
     * 设置弹出菜单AnimaionStyle
     *
     * @param animStyle
     */
    public AmayaToolBar setMTPopupWindowAnim(int animStyle) {
        amayaToolBar.setPopupWindowAnim(animStyle);
        return amayaToolBar;
    }
}
