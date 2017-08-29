package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者: shantoo on 2017/8/29 15:46.
 */

public class UISettings extends Fragment{

    private static String TAG;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return textView;
    }
}
