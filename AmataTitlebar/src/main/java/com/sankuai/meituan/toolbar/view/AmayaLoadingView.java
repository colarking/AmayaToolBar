package com.sankuai.meituan.toolbar.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AmayaLoadingView extends FrameLayout {

    private TextView showText;
    private ProgressBar amayaBar;

    public AmayaLoadingView(Context context) {
        super(context);
        initView(context);
    }

    public AmayaLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.amaya_footer_view, null);
        amayaBar = (ProgressBar) view.findViewById(R.id.amaya_footer_bar);
        showText = (TextView) view.findViewById(R.id.amaya_empty_text);
        showText.setText(R.string.amaya_loading);
        addView(view);
    }

    public void showResultText(String text) {
        showText.setVisibility(View.VISIBLE);
        amayaBar.setVisibility(View.GONE);
        if (TextUtils.isEmpty(text)) {
            showResultText(R.string.amaya_loading_empty);
        } else {
            showText.setText(text);
        }

    }

    public void showResultText(int text) {
        showText.setVisibility(View.VISIBLE);
        amayaBar.setVisibility(View.GONE);
        showText.setText(text);
    }


    public boolean isLoading() {
        return amayaBar.getVisibility() == View.VISIBLE;
    }

    public void startLoading() {
        amayaBar.setVisibility(View.VISIBLE);
        showText.setText(R.string.amaya_loading);
    }

    public void hideLoading() {
        amayaBar.setVisibility(View.GONE);
    }
}
