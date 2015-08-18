package com.nqm.loginbuilding;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ton on 7/21/2015 AD.
 */
public class ActionTabsViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public static final int BUILDING = 0;
    public static final int DEVICE = 1;
    public static final int SESSION = 2;
    //public static final int PARTY = 3;
    public static final String UI_TAB_BUILDING = "BUILDING";
    public static final String UI_TAB_DEVICE = "DEVICE";
    public static final String UI_TAB_SESSION = "SESSION";
    //public static final String UI_TAB_PARTY = "PART";

    public ActionTabsViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int pos){
        return fragments.get(pos);
    }

    public int getCount(){
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case BUILDING:
                return UI_TAB_BUILDING;
            case DEVICE:
                return UI_TAB_DEVICE;
            case SESSION:
                return UI_TAB_SESSION;
//            case PARTY:
//                return UI_TAB_PARTY;
            default:
                break;
        }
        return null;
    }
}
