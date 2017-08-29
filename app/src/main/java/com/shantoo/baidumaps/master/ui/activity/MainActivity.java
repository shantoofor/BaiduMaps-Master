package com.shantoo.baidumaps.master.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shantoo.baidumaps.master.R;
import com.shantoo.baidumaps.master.ui.fragment.main.BaiduLocationFragment;
import com.shantoo.baidumaps.master.ui.fragment.main.BaiduMapFragment;
import com.shantoo.baidumaps.master.ui.fragment.main.BaiduNavigationFragment;
import com.shantoo.develop.library.ui.widget.navigationview.BottomNavigationViewEx;
import com.shantoo.develop.library.utils.FragmentBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.shantoo.baidumaps.master.ui.activity.MainActivity.TAG.BAIDU_MAP;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView leftNavigation;
    private BottomNavigationViewEx bottomNavigation;
    public FragmentBuilder<Fragment> mFragmentBuilder;

    public static class TAG {
        public final static int BAIDU_MAP = 0;
        public final static int BAIDU_LOCATION = 1;
        public final static int BAIDU_NAVIGATION = 2;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initViews();
        initToolbar();
        initMenu();
        initListener();
        initDrawable();
    }

    //初始化
    private void init() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BaiduMapFragment());
        fragmentList.add(new BaiduLocationFragment());
        fragmentList.add(new BaiduNavigationFragment());

        mFragmentBuilder = new FragmentBuilder<>();
        mFragmentBuilder
                .with(getSupportFragmentManager(), fragmentList, R.id.id_content)
                .setCurrentFragment(BAIDU_MAP);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftNavigation = (NavigationView) findViewById(R.id.left_navigation);
        bottomNavigation = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation);
        //goBackHome = (TextView) leftMenu.getHeaderView(0).findViewById(R.id.go_back_home);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initMenu() {
        List<MenuItem> mMenuList = new ArrayList<>();
        //leftNavigation.setItemIconTintList(null);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
        Menu menu = bottomNavigation.getMenu();
        for (int index = 0; index < menu.size(); index++) {
            mMenuList.add(menu.getItem(index));
        }
    }

    //初始化侧边栏
    private void initDrawable() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initListener() {
        leftNavigation.setNavigationItemSelectedListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        // goBackHome.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_action_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_release:
                Toast.makeText(this, "nav_release", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_order:
                Toast.makeText(this, "nav_order", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_wallet:
                Toast.makeText(this, "nav_wallet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_messages:
                Toast.makeText(this, "nav_messages", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_owner_attestation:
                Toast.makeText(this, "nav_owner_attestation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_partner:
                Toast.makeText(this, "nav_partner", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "nav_share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_guide:
                Toast.makeText(this, "nav_guide", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "nav_settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_baidu_map:
                mFragmentBuilder.createFragment(TAG.BAIDU_MAP);
                break;
            case R.id.navigation_baidu_location:
                mFragmentBuilder.createFragment(TAG.BAIDU_LOCATION);
                break;
            case R.id.navigation_baidu_navigation:
                mFragmentBuilder.createFragment(TAG.BAIDU_NAVIGATION);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}