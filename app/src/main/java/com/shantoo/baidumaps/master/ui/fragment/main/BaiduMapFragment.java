package com.shantoo.baidumaps.master.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shantoo.baidumaps.master.R;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.BaseMap;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.GeoCode;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.Geometry;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.HeatMap;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.Layers;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.Location;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.MapControl;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.MapFragment;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.MultiMapView;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.Overlay;
import com.shantoo.baidumaps.master.ui.fragment.baidumap.UISettings;
import com.shantoo.develop.library.ui.widget.viewpager.ViewPagerBinder;

public class BaiduMapFragment extends Fragment {

    private String[] titles = {
            "BaseMap",
            "MapFragment",
            "Layers",
            "MultiMapView",
            "MapControl",
            "UISettings",
            "Location",
            "Geometry",
            "Overlay",
            "HeatMap",
            "GeoCode"};

    private Fragment[] fragments = {
            new BaseMap(),
            new MapFragment(),
            new Layers(),
            new MultiMapView(),
            new MapControl(),
            new UISettings(),
            new Location(),
            new Geometry(),
            new Overlay(),
            new HeatMap(),
            new GeoCode()
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baidu_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.id_table_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.id_view_pager);

        ViewPagerBinder.getInstance().onBind(viewPager, tabLayout, getChildFragmentManager(), titles, fragments);
    }
}
