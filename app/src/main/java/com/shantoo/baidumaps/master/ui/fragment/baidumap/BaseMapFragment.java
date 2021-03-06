package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者: shantoo on 2017/8/29 15:46.
 */

public class BaseMapFragment extends Fragment {

    private MapView mMapView;
    private static String TAG;
    private FrameLayout layout;
    private static final int OPEN_ID = 0;
    private static final int CLOSE_ID = 1;
    private boolean mEnableCustomStyle = true;
    //用于设置个性化地图的样式文件
    // 提供三种样式模板："custom_config_blue.txt"，"custom_config_dark.txt"，"custom_config_midnightblue.txt"
    private static String PATH = "custom_config_dark.txt";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapStatus.Builder builder = new MapStatus.Builder();
        LatLng center = new LatLng(39.915071, 116.403907); // 默认 天安门
        float zoom = 11.0f; // 默认 11级
        Intent intent = getActivity().getIntent();
        if (null != intent) {
            mEnableCustomStyle = intent.getBooleanExtra("customStyle", true);
            center = new LatLng(intent.getDoubleExtra("y", 39.915071),
                    intent.getDoubleExtra("x", 116.403907));
            zoom = intent.getFloatExtra("level", 11.0f);
        }
        builder.target(center).zoom(zoom);

        setMapCustomFile(getActivity(), PATH);
        mMapView = new MapView(getActivity(), new BaiduMapOptions());


        initView(getContext());
        MapView.setMapCustomEnable(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return layout;
    }

    // 初始化View
    private void initView(Context context) {
        layout = new FrameLayout(getContext());
        layout.addView(mMapView);
        RadioGroup group = new RadioGroup(context);
        group.setBackgroundColor(Color.BLACK);
        final RadioButton openBtn = new RadioButton(context);
        openBtn.setText("开启个性化地图");
        openBtn.setId(OPEN_ID);
        openBtn.setTextColor(Color.WHITE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        group.addView(openBtn, params);
        final RadioButton closeBtn = new RadioButton(context);
        closeBtn.setText("关闭个性化地图");
        closeBtn.setTextColor(Color.WHITE);
        closeBtn.setId(CLOSE_ID);
        group.addView(closeBtn, params);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        if (mEnableCustomStyle) {
            openBtn.setChecked(true);
        } else {
            closeBtn.setChecked(true);
        }

        layout.addView(group, layoutParams);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == OPEN_ID) {
                    MapView.setMapCustomEnable(true);
                } else if (checkedId == CLOSE_ID) {
                    MapView.setMapCustomEnable(false);
                }
            }
        });
    }

    // 设置个性化地图config文件路径
    private void setMapCustomFile(Context context, String PATH) {
        FileOutputStream out = null;
        InputStream inputStream = null;
        String moduleName = null;
        try {
            inputStream = context.getAssets()
                    .open("customConfigdir/" + PATH);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            moduleName = context.getFilesDir().getAbsolutePath();
            File f = new File(moduleName + "/" + PATH);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            out = new FileOutputStream(f);
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MapView.setCustomMapStylePath(moduleName + "/" + PATH);
    }
}
