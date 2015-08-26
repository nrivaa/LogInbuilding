package com.nqm.loginbuilding;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.print.PrintJob;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nqm.loginbuilding.view.SlidingTabLayout;

import org.w3c.dom.Text;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectDetailActivity extends Activity {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ActionTabsViewPagerAdapter myViewPageAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_activity_fragment);

        Project.getInstance().GetProjectDetail(Project.getInstance().getBuildingID());

        // Define SlidingTabLayout (shown at top)
        // and ViewPager (shown at bottom) in the layout.
        // Get their instances.
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);

        // create a fragment list in order.
        fragments = new ArrayList<Fragment>();
        fragments.add(new ProjectDetailBuildingFragment());
        fragments.add(new ProjectDetailTestDeviceFragment());
        fragments.add(new ProjectDetailTestSessionFragment());
        //fragments.add(new PartyFragment());

        // use FragmentPagerAdapter to bind the slidingTabLayout (tabs with different titles)
        // and ViewPager (different pages of fragment) together.
        myViewPageAdapter =new ActionTabsViewPagerAdapter(getFragmentManager(),
                fragments);
        viewPager.setAdapter(myViewPageAdapter);

        // make sure the tabs are equally spaced.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.menu_project_detail_activity_flagment, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menuitem_search:
//                Toast.makeText(this, getString(R.string.ui_menu_search),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_send:
//                Toast.makeText(this, getString(R.string.ui_menu_send),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_add:
//                Toast.makeText(this, getString(R.string.ui_menu_add),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_share:
//                Toast.makeText(this, getString(R.string.ui_menu_share),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_feedback:
//                Toast.makeText(this, getString(R.string.ui_menu_feedback),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_about:
//                Toast.makeText(this, getString(R.string.ui_menu_about),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuitem_quit:
//                Toast.makeText(this, getString(R.string.ui_menu_quit),
//                        Toast.LENGTH_SHORT).show();
//                finish(); // close the activity
//                return true;
//        }
        return false;
    }



}
