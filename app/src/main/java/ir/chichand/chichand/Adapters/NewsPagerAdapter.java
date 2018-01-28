package ir.chichand.chichand.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ir.chichand.chichand.Fragments.Fragment_News_Category;

/**
 * Created by bSherafati on 1/28/2018.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment_News_Category newsCatFragment0 = new Fragment_News_Category();
                return newsCatFragment0;
            case 1:
                Fragment_News_Category newsCatFragment1 = new Fragment_News_Category();
                return newsCatFragment1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ورزشی";
            case 1:
                return "اقتصادی";
            default:
                return null;
        }
    }
}
