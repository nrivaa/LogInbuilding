package com.nqm.loginbuilding;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sirawang on 14/07/2015.
 */
public class MyPagerAdapter  extends PagerAdapter {

    public int getCount() {
        return 3;
    }

    public Object instantiateItem(View collection, int position) {

        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = 0;
        switch (position) {
            case 0:
                resId = showHotelContact();
                break;
            case 1:
                resId = showHotelAddress();
                break;
            case 2:
                resId = showHotelMap();
                break;
        }

        View view = inflater.inflate(resId, null);
        ((ViewPager) collection).addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    public int showHotelMap()
    {
        int resId;
        resId = R.layout.activity_create_project;
        Log.e("Swipe","createProject");
        return resId;
    }
    public int showHotelAddress()
    {
        int resId;
        resId = R.layout.activity_add_session;
        Log.e("Swipe","addsession");
        return resId;
    }
    public int showHotelContact()
    {
        int resId;
        resId = R.layout.activity_edit_project;
        Log.e("Swipe","editproject");
        return resId;
    }
}


