package com.sankuai.meituan.toolbar.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.sankuai.meituan.bean.AmayaItem;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:28
 */
public class MainActivity extends AmayaActivity implements View.OnClickListener, AmayaMenuClickListener {

    private boolean showLoading;
    private boolean backVisible;
    private int[] logos = new int[]{R.drawable.amaya_icon, R.drawable.amaya_icon_2};
    private int logoIndex;
    private int[] titleBGs = new int[]{Color.CYAN, Color.BLACK, Color.TRANSPARENT, Color.BLUE, Color.DKGRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED};
    private int titleBgIndex;
    private int[] items = new int[]{android.R.drawable.ic_menu_camera, android.R.drawable.ic_media_play};
    private int itemIndex;
    private int testTitle;
    private boolean showLogoVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, true);

//        addItem(new AmayaItem(0,android.R.drawable.ic_menu_camera,R.string.app_name));
//


    }

    @Override
    public void onClick(View v) {
        Log.e("amaya", "onClick()...v=" + v);
        switch (v.getId()){
            case R.id.amaya_btn_title_bg:

                amayaTitleBg();
                break;
            case R.id.amaya_btn_title_back:
                amayaBackVisible();
                break;
            case R.id.amaya_btn_title_name:
                amayaTitle();
                break;
            case R.id.amaya_btn_title_color:
                amayaTitleColor();
                break;
            case R.id.amaya_btn_title_logo_change:
                amayaLogo();
                break;
            case R.id.amaya_btn_title_logo_loading:
                amayaLoading();
                break;
            case R.id.amaya_btn_title_logo_visible:
                amayaLogoVisible();
                break;
            case R.id.amaya_btn_title_add_item:
                amayaAddItem();
                break;
            case R.id.amaya_btn_title_clear_item:
                amayaClearItem();
                break;
            case R.id.amaya_btn_title_item_selector:
                amayaItemSelector();
                break;
            case R.id.amaya_btn_title_pop_bg:
                amayaPopupBg();
                break;
            case R.id.amaya_btn_title_pop_anim:
                amayaPopupAnim();
                break;
        }
    }

    private void amayaPopupAnim() {
        setMTPopupWindowAnim(R.style.AmayaPopupAnimation2);
    }


    /**
     * 设置Popwindow背景资源
     */
    private void amayaPopupBg() {
        setMTPopWindowBg(R.drawable.actionbar_bg_pop_change);
    }


    /**
     * 设置点击Selector
     */
    private void amayaItemSelector() {
        setMTItemBackdround(R.drawable.amaya_item_bg_change_selector);
    }

    /**
     * 显示/隐藏缓冲框提示
     */
    private void amayaLoading() {
        showMTLoading(showLoading);
        showLoading = !showLoading;
    }

    /**
     * 设置标题颜色
     */
    private void amayaTitleColor() {
        setMTTitleColor(Color.MAGENTA);
    }

    /**
     * 设置标题
     */
    private void amayaTitle() {
        setMTTitle("标题" + testTitle++);
    }

    /**
     * 增加MenuItem
     */
    private void amayaAddItem() {
        addMTItem(new AmayaItem(itemIndex, android.R.drawable.ic_media_play, R.string.app_name), itemIndex);
        itemIndex++;
        /**
         * 记得一定要调用回调接口
         */
        addMTItemClick(this);
    }

    /**
     * 清除MenuItem
     */
    private void amayaClearItem() {
        clearMTItem();
    }


    /**
     * 设置标题背景
     */
    private void amayaTitleBg() {
        setMTActionBarBackgroundColor(titleBGs[titleBgIndex]);
        if (++titleBgIndex == titleBGs.length) titleBgIndex = 0;
    }

    /**
     * 设置返回小图标显示/隐藏
     */
    private void amayaBackVisible() {
        setMTBackViewVisible(backVisible);
        backVisible = !backVisible;
    }

    /**
     * 显示/隐藏 LOGO图标
     */
    private void amayaLogoVisible() {
        setMTLogoVisible(showLogoVisible);
        showLogoVisible = !showLogoVisible;
    }

    /**
     * 更改LOGO图标
     */
    private void amayaLogo() {

        setMTLogo(logos[logoIndex]);
        if (logoIndex == 0) {
            logoIndex = 1;
        } else {
            logoIndex = 0;
        }
    }

    @Override
    public void onAmayaItemClick(int itemId) {
        Toast.makeText(this, "onAmayaItemClick()...itemId=" + itemId, Toast.LENGTH_LONG).show();
    }
}
