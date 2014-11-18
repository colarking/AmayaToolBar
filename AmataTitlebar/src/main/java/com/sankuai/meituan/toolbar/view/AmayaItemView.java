package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sankuai.meituan.bean.AmayaItem;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-17
 * Time: 下午3:18
 */
public class AmayaItemView extends LinearLayout {

    public AmayaItemView(Context context) {
        super(context);
        initView(context, null);
    }

    public AmayaItemView(Context context, AmayaItem item, int drawable) {
        super(context);
        initView(context, item);
        setBackgroundResource(drawable);
    }

    public AmayaItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AmayaItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, null);
    }

    @TargetApi(Build.VERSION_CODES.L)
    public AmayaItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, null);
    }

    private void initView(Context context, AmayaItem item) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(10, 20, 10, 20);


        if (item != null && item.showImg) {
            ImageView img = new ImageView(context);
            float dp24 = getResources().getDimension(R.dimen.amaya_title_item_height);
            LayoutParams lp1 = new LayoutParams((int) dp24, (int) dp24);
            addView(img, lp1);
            img.setImageResource(item.imgRes);
        }
        TextView txt = new TextView(context);
        txt.setTextColor(Color.WHITE);
        LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        txt.setPadding(10, 10, 10, 10);
        addView(txt, lp2);
        if (item != null) {
            txt.setText(item.txtRes);
        }
    }


    public void updateView(AmayaItem item) {
        ImageView img = (ImageView) getChildAt(0);
        if (img != null) {
            img.setImageResource(item.imgRes);
        }
        TextView tv = (TextView) getChildAt(1);
        if (tv != null) {
            tv.setText(item.txtRes);
        }
    }
}
