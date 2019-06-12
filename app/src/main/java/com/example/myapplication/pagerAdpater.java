package com.example.myapplication;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static android.provider.Settings.System.getString;


public class pagerAdpater extends FragmentPagerAdapter {
    Context c;
    public pagerAdpater(FragmentManager fm, Context c) {
        super(fm);
        this.c=c;
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i){

            case 0:return new NumbersFragment();
            case 1:return new ColorFragment();
            case 2:return new FamilyFragment();
            case 3:return new PhrasesFragment();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:return c.getString(R.string.category_numbers);
            case 1:return c.getString(R.string.category_colors);
            case 2:return c.getString(R.string.category_family);
            case 3:return c.getString(R.string.category_phrases);
            default:return null;
        }    }
}
