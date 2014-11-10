package com.sankuai.meituan.toolbar.view;

import android.os.Bundle;
import android.view.View;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:28
 */
public class MainActivity extends AmayaActivity implements View.OnClickListener {

    private boolean testHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.amaya_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.amaya_btn:
                showLoading(testHide);
                testHide = !testHide;
                break;
        }
    }
}
