package yeell.yeellgank.views.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import yeell.yeellgank.R;
import yeell.yeellgank.adpter.HomeFragmentPagerAdapter;
import yeell.yeellgank.base.BaseActivity;
import yeell.yeellgank.https.ApiManager;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private int mCurrentPosition = 0;
    private HomeFragmentPagerAdapter mAdapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initView(savedInstanceState);
        initTableLayout();
    }

    private void initView(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    //初始化TableLayout
    private void initTableLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0 ; i<7;i++) {
            switch (i){
                case 0:
                    titleList.add(ApiManager.API_DATA_TYPE_ALL);
                    break;
                case 1:
                    titleList.add(ApiManager.API_DATA_TYPE_ANDROID);
                    break;
                case 2:
                    titleList.add(ApiManager.API_DATA_TYPE_iOS);
                    break;
                case 3:
                    titleList.add(ApiManager.API_DATA_TYPE_FULI);
                    break;
                case 4:
                    titleList.add(ApiManager.API_DATA_TYPE_QIANDUAN);
                    break;
                case 5:
                    titleList.add(ApiManager.API_DATA_TYPE_TUOZHAN);
                    break;
                case 6:
                    titleList.add(ApiManager.API_DATA_TYPE_XIUXI);
                    break;
                case 7:
                    titleList.add(ApiManager.API_DATA_TYPE_XIATUIJIAN);
                    break;
                case 8:
                    titleList.add(ApiManager.API_DATA_TYPE_APP);
                    break;
            }
        }

        ArrayList<Fragment> listFragment = new ArrayList<>();
        for (int i = 0 ; i<7;i++) {
            TypeFragment typeFragment = new TypeFragment();
            Bundle bundle = new Bundle();
            switch (i){
                case 0:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_ALL);
                    break;
                case 1:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_ANDROID);
                    break;
                case 2:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_iOS);
                    break;
                case 3:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_FULI);
                    break;
                case 4:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_QIANDUAN);
                    break;
                case 5:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_TUOZHAN);
                    break;
                case 6:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_XIUXI);
                    break;
                case 7:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_XIATUIJIAN);
                    break;
                case 8:
                    bundle.putString(TypeFragment.TYPE_DATA,ApiManager.API_DATA_TYPE_APP);
                    break;
            }
            typeFragment.setArguments(bundle);
            listFragment.add(typeFragment);
        }
        mAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), titleList, listFragment);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(mCurrentPosition);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
