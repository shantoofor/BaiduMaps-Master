package com.shantoo.baidumaps.master.ui.fragment.baidumap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.SupportMapFragment;
import com.shantoo.baidumaps.master.R;

/**
 * 作者: shantoo on 2017/8/29 15:46.
 */

public class MapFragmentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(-20).zoom(15);
        BaiduMapOptions bo = new BaiduMapOptions().mapStatus(builder.build())
                .compassEnabled(false).zoomControlsEnabled(false);
        SupportMapFragment map = SupportMapFragment.newInstance(bo);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().add(R.id.map, map, "map_fragment").commit();
    }
}