package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.shantoo.baidumaps.master.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 作者: shantoo on 2017/8/30 16:49.
 */

public class HeatMapFragment extends Fragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private HeatMap heatmap;
    private Button mAdd;
    private Button mRemove;
    private boolean isDestroy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heatmap, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) view.findViewById(R.id.mapview);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(5));
        mAdd = (Button) view.findViewById(R.id.add);
        mRemove = (Button) view.findViewById(R.id.remove);
        mAdd.setEnabled(false);
        mRemove.setEnabled(false);
        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addHeatMap();
            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                heatmap.removeHeatMap();
                mAdd.setEnabled(true);
                mRemove.setEnabled(false);
            }
        });
        addHeatMap();
    }

    private void addHeatMap() {
        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (!isDestroy) {
                    mBaiduMap.addHeatMap(heatmap);
                }
                mAdd.setEnabled(false);
                mRemove.setEnabled(true);
            }
        };
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<LatLng> data = getLocations();
                heatmap = new HeatMap.Builder().data(data).build();
                h.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
    }

    private List<LatLng> getLocations() {
        List<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(R.raw.locations);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array;
        try {
            array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                double lat = object.getDouble("lat");
                double lng = object.getDouble("lng");
                list.add(new LatLng(lat, lng));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
