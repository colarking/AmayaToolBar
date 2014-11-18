package com.sankuai.meituan.bean;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-11
 * Time: 上午11:29
 */
public class AmayaItem {

    public int itemId;
    public int imgRes;
    public int txtRes;
    public boolean showImg;


    public AmayaItem(int itemId, int imgRes, int txtRes) {
        this.itemId = itemId;
        this.imgRes = imgRes;
        this.txtRes = txtRes;
    }

    public void setShowImg(boolean showImg) {
        this.showImg = showImg;
    }

}
