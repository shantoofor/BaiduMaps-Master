package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.UiSettings;
import com.shantoo.baidumaps.master.R;

import static com.baidu.location.d.j.v;
import static com.shantoo.baidumaps.master.R.id.setPadding;

/**
 * 作者: shantoo on 2017/8/29 15:46.
 */

public class UISettingsFragment extends Fragment implements View.OnClickListener{

    /**
     * MapView 是地图主控件
     */
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private UiSettings mUiSettings;
    private static final int paddingLeft = 0;
    private static final int paddingTop = 0;
    private static final int paddingRight = 0;
    private static final int paddingBottom = 200;
    TextView mTextView;
    private CheckBox cbAllGestures;
    private CheckBox cbZoom;
    private CheckBox cbOverlook;
    private CheckBox cbRotate;
    private CheckBox cbScroll;
    private CheckBox compass;
    private CheckBox mappoi;
    private CheckBox setPadding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uisetting,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mUiSettings = mBaiduMap.getUiSettings();
        cbAllGestures = (CheckBox) view.findViewById(R.id.allGesture);
        cbZoom = (CheckBox) view.findViewById(R.id.zoom);
        cbScroll = (CheckBox) view.findViewById(R.id.scroll);
        cbOverlook = (CheckBox) view.findViewById(R.id.overlook);
        cbRotate = (CheckBox) view.findViewById(R.id.rotate);
        MapStatus ms = new MapStatus.Builder().build();
        MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
        mBaiduMap.animateMapStatus(u, 1000);

        mMapView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 介绍获取比例尺的宽高，需在MapView绘制完成之后
                int scaleControlViewWidth = mMapView.getScaleControlViewWidth();
                int scaleControlViewHeight = mMapView.getScaleControlViewHeight();
            }
        }, 0);

        view.findViewById(R.id.zoom).setOnClickListener(this);
        view.findViewById(R.id.scroll).setOnClickListener(this);
        view.findViewById(R.id.rotate).setOnClickListener(this);
        view.findViewById(R.id.overlook).setOnClickListener(this);
        compass = (CheckBox) view.findViewById(R.id.compass);
        compass.setOnClickListener(this);
        mappoi = (CheckBox) view.findViewById(R.id.mappoi);
        mappoi.setOnClickListener(this);
        view.findViewById(R.id.allGesture).setOnClickListener(this);
        setPadding = (CheckBox) view.findViewById(R.id.setPadding);
        setPadding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.zoom:
                setZoomEnable();
                break;
            case R.id.scroll:
                setScrollEnable();
                break;
            case R.id.rotate:
                setRotateEnable();
                break;
            case R.id.overlook:
                setOverlookEnable();
                break;
            case R.id.compass:
                setCompassEnable();
                break;
            case R.id.mappoi:
                setMapPoiEnable();
                break;
            case R.id.allGesture:
                setAllGestureEnable();
                break;
            case R.id.setPadding:
                setPadding();
                break;
            default:
                break;
        }
    }

    /**
     * 是否启用缩放手势
     */
    public void setZoomEnable() {
        updateGesture();
    }

    /**
     * 是否启用平移手势
     */
    public void setScrollEnable() {
        updateGesture();
    }

    /**
     * 是否启用旋转手势
     */
    public void setRotateEnable() {
        updateGesture();
    }

    /**
     * 是否启用俯视手势
     */
    public void setOverlookEnable() {
        updateGesture();
    }

    /**
     * 是否启用指南针图层
     */
    public void setCompassEnable() {
        mUiSettings.setCompassEnabled(compass.isChecked());
    }

    /**
     * 是否显示底图默认标注
     */
    public void setMapPoiEnable() {
        mBaiduMap.showMapPoi(mappoi.isChecked());
    }

    /**
     * 禁用所有手势
     */
    public void setAllGestureEnable() {
        updateGesture();
    }

    /**
     * 更新手势状态
     */
    public void updateGesture() {
        if (cbAllGestures.isChecked()) {
            mUiSettings.setAllGesturesEnabled(!cbAllGestures.isChecked());
        } else {
            mUiSettings.setZoomGesturesEnabled(cbZoom.isChecked());
            mUiSettings.setScrollGesturesEnabled(cbScroll.isChecked());
            mUiSettings.setRotateGesturesEnabled(cbRotate.isChecked());
            mUiSettings.setOverlookingGesturesEnabled(cbOverlook.isChecked());
        }
    }

    /**
     * 设置Padding区域
     */
    public void setPadding() {
        if (setPadding.isChecked()) {
            mBaiduMap.setViewPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            addView(mMapView);
        } else {
            mBaiduMap.setViewPadding(0, 0, 0, 0);
            mMapView.removeView(mTextView);
        }
    }

    private void addView(MapView mapView) {
        mTextView = new TextView(getContext());
        mTextView.setText(getText(R.string.instruction));
        mTextView.setTextSize(15.0f);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setBackgroundColor(Color.parseColor("#AA00FF00"));

        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.absoluteMode);
        builder.width(mapView.getWidth());
        builder.height(paddingBottom);
        builder.point(new Point(0, mapView.getHeight()));
        builder.align(MapViewLayoutParams.ALIGN_LEFT, MapViewLayoutParams.ALIGN_BOTTOM);

        mapView.addView(mTextView, builder.build());
    }

    @Override
    public void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mMapView.onDestroy();
        super.onDestroy();
    }
}
