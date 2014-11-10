package com.tekinarslan.material.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mikepenz.lollipopshowcase.MainActivity;
import com.sankuai.meituan.toolbar.R;

public class SampleFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_POSITION = "position";

    private int position;

    public static SampleFragment newInstance(int position) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.page, container, false);

        ProgressBarCircular progressBarCircular = (ProgressBarCircular) rootView.findViewById(R.id.progress);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabButton);
        fab.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        fab.setOnClickListener(this);
        switch (position) {
            case 0:
                fab.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                break;
            case 1:
                fab.setBackgroundColor(getResources().getColor(R.color.red));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case 2:
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.blue));
                fab.setBackgroundColor(getResources().getColor(R.color.blue));

                break;
            case 3:
                fab.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                break;
            case 4:
                int color = Color.parseColor("#aa66cc");
                fab.setBackgroundColor(color);
                progressBarCircular.setBackgroundColor(color);
                break;
            case 7:
                color = Color.parseColor("#8877dd");
                fab.setBackgroundColor(color);
                progressBarCircular.setBackgroundColor(color);
                break;
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabButton:
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
                break;
        }
    }
}