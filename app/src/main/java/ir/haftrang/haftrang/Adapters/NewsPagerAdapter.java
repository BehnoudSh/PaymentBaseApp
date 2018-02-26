package ir.haftrang.haftrang.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import ir.haftrang.haftrang.Fragments.Fragment_News_Category;
import ir.haftrang.haftrang.Models.Responses.Response_Categories;

/**
 * Created by bSherafati on 1/28/2018.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    List<Response_Categories> news_cat_level1_list;

    public NewsPagerAdapter(FragmentManager fm, List<Response_Categories> news_cat_level1_list) {
        super(fm);
        this.news_cat_level1_list = news_cat_level1_list;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment_News_Category newsCatFragment = new Fragment_News_Category();

        return newsCatFragment;

    }

    @Override
    public int getCount() {
        return news_cat_level1_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return this.news_cat_level1_list.get(position).getPersian_title();

//        for (Response_Categories cat_item : this.news_cat_level1_list
//                ) {
//
//        }


        //return null;
    }
}
