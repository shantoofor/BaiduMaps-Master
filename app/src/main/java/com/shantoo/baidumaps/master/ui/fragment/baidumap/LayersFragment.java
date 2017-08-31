package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.shantoo.baidumaps.master.R;

/**
 * 作者: shantoo on 2017/8/29 15:50.
 */

public class LayersFragment extends Fragment implements View.OnClickListener{

    /**
     * MapView 是地图主控件
     */
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private CheckBox traffice,baiduHeatMap;
    private RadioButton normal,statellite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //设置底图显示模式
        normal = (RadioButton) view.findViewById(R.id.normal);
        statellite = (RadioButton) view.findViewById(R.id.statellite);
        normal.setOnClickListener(this);
        statellite.setOnClickListener(this);

        traffice = (CheckBox) view.findViewById(R.id.traffice);
        baiduHeatMap = (CheckBox) view.findViewById(R.id.baiduHeatMap);
        traffice.setOnClickListener(this);
        baiduHeatMap.setOnClickListener(this);
    }

    /**
     * 设置底图显示模式为普通图
     * */
    public void setNormal(){
        if (normal.isChecked()) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        }
    }

    /**
     * 设置底图显示模式为卫星图
     * */
    public void setStatellite(){
        if (statellite.isChecked()) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        }
    }

    /**
     * 设置是否显示交通图
     *
     */
    public void setTraffic() {
        mBaiduMap.setTrafficEnabled(traffice.isChecked());
    }

    /**
     * 设置是否显示百度热力图
     *
     */
    public void setBaiduHeatMap() {
        mBaiduMap.setBaiduHeatMapEnabled(baiduHeatMap.isChecked());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.traffice:
                setTraffic();
                break;
            case R.id.baiduHeatMap:
                setBaiduHeatMap();
                break;
            case R.id.normal:
                setNormal();
                break;
            case R.id.statellite:
                setStatellite();
                break;
            default:
                break;
        }
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
