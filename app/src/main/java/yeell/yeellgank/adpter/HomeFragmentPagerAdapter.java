package yeell.yeellgank.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yee on 11/22/16.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTile;
    private List<Fragment> mFragment;

    public HomeFragmentPagerAdapter(FragmentManager fm, List<String> mTile, List<Fragment> mFragment) {
        super(fm);
        this.mTile = mTile;
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTile.get(position % mTile.size());
    }
}
