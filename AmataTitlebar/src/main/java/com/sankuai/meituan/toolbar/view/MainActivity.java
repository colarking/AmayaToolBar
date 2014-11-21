package com.sankuai.meituan.toolbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:28
 */
public class MainActivity extends AmayaActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.amaya_btn_orverlay:
                Intent in = new Intent(this, FrameToolActivity.class);
                startActivity(in);
                break;
            case R.id.amaya_btn_below:
                in = new Intent(this, LinearToolActivity.class);
                startActivity(in);

                break;
        }

    }
}
