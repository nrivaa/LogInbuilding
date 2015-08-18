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


//public class ProjectDetailActivity extends ActionBarActivity {
//
//    private Context ctx;
//    private ListView listView;
//    private List<String> sessions;
//    private ArrayAdapter<String> itemsAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_project_detail);
//        Project.getInstance().setCtx(ProjectDetailActivity.this);
//
//        // Add tab page
//
//        MyPagerAdapter adapter = new MyPagerAdapter();
//        ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
//        myPager.setAdapter(adapter);
//        myPager.setCurrentItem(3);
//
//
//        ctx = getApplicationContext();
//
//
//        sessions = new ArrayList<>();
////        sessions.add("Session 1");
////        sessions.add("Session 2");
////        sessions.add("Session 3");
////        sessions.add("Session 4");
////        sessions.add("Session 5");
////        sessions.add("Session 6");
////        sessions.add("Session 7");
////        sessions.add("Session 8");
//
//        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sessions);
//
//        listView = (ListView)findViewById(R.id.projectDetail_Session_List);
//        listView.setAdapter(itemsAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(ctx,SessionDetailActivity.class);
//                i.putExtra("position",position);
//                startActivity(i);
//                //Log.e("Selected Project",listView.getItemAtPosition(position).toString());
//                //Project project = new Project(ProjectListActivity.this);
//                //project.GetProjectDetail(listView.getItemAtPosition(position).toString());
//
//            }
//        });
//
//        Button btn_add_session = (Button)findViewById(R.id.projectDetail_btn_add_session);
//        btn_add_session.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ctx,AddSessionActivity.class);
//                startActivity(i);
//            }
//        });
//
//        SetProjectInfo();
//    }
//
//    private TextView tv_BuildingID;
//    private TextView tv_Name;
//    private TextView tv_Service;
//    private TextView tv_accessSite;
//    private TextView tv_contactName;
//    private TextView tv_contactNumber;
//    private TextView tv_imei_awn;
//    private TextView tv_imei_dtac;
//    private TextView tv_imei_trueh;
//    private TextView tv_imei_3bb;
//    private TextView tv_address;
//    private TextView tv_tambon;
//    private TextView tv_district;
//    private TextView tv_province;
//    private TextView tv_postcode;
//    private TextView tv_buildingDetail;
//    private TextView tv_createDate;
//    private TextView tv_EditDate;
//    private TextView tv_latitude;
//    private TextView tv_longitude;
//    private TextView tv_user;
//    private TextView tv_lac;
//    private TextView tv_cid;
//
//
//    public void SetProjectInfo(){
//        // Assign Control ID
//        tv_BuildingID = (TextView)findViewById(R.id.projectDetail_BuildingID);
//        tv_Name=(TextView)findViewById(R.id.projectDetail_Name);
//        tv_Service=(TextView)findViewById(R.id.projectDetail_Service);
//        tv_accessSite=(TextView)findViewById(R.id.projectDetail_accessSite);
//        tv_contactName=(TextView)findViewById(R.id.projectDetail_contactName);
//        tv_contactNumber=(TextView)findViewById(R.id.projectDetail_contactNumber);
//        tv_imei_awn=(TextView)findViewById(R.id.projectDetail_imei_awn);
//        tv_imei_dtac=(TextView)findViewById(R.id.projectDetail_imei_dtac);
//        tv_imei_trueh=(TextView)findViewById(R.id.projectDetail_imei_trueh);
//        tv_imei_3bb=(TextView)findViewById(R.id.projectDetail_imei_3bb);
//        tv_address=(TextView)findViewById(R.id.projectDetail_address);
//        tv_tambon=(TextView)findViewById(R.id.projectDetail_tambon);
//        tv_district=(TextView)findViewById(R.id.projectDetail_district);
//        tv_province=(TextView)findViewById(R.id.projectDetail_province);
//        tv_postcode=(TextView)findViewById(R.id.projectDetail_postcode);
//        tv_buildingDetail=(TextView)findViewById(R.id.projectDetail_BuildingDetail);
//        tv_createDate=(TextView)findViewById(R.id.projectDetail_createDate);
//        tv_EditDate=(TextView)findViewById(R.id.projectDetail_editDate);
//        tv_latitude=(TextView)findViewById(R.id.projectDetail_latitude);
//        tv_longitude=(TextView)findViewById(R.id.projectDetail_longitude);
//        tv_user=(TextView)findViewById(R.id.projectDetail_user);
//        tv_lac=(TextView)findViewById(R.id.projectDetail_lac);
//        tv_cid=(TextView)findViewById(R.id.projectDetail_cid);
//
//        // Assign value to control
//        tv_BuildingID.setText(Project.getInstance().getBuildingID());
//        tv_Name.setText(Project.getInstance().getName());
//        tv_Service.setText(Project.getInstance().getService());
//        tv_accessSite.setText(String.valueOf(Project.getInstance().getAccessSite()));
//        tv_contactName.setText(Project.getInstance().getContactName());
//        tv_contactNumber.setText(Project.getInstance().getContactNumber());
//        tv_imei_awn.setText(Project.getInstance().getIMEI_AWN());
//        tv_imei_dtac.setText(Project.getInstance().getIMEI_DTAC());
//        tv_imei_trueh.setText(Project.getInstance().getIMEI_TRUEH());
//        tv_imei_3bb.setText(Project.getInstance().getIMEI_3BB());
//        tv_address.setText(Project.getInstance().getAddress());
//        tv_tambon.setText(Project.getInstance().getTambon());
//        tv_district.setText(Project.getInstance().getDistrict());
//        tv_province.setText(Project.getInstance().getProvince());
//        tv_postcode.setText(Project.getInstance().getPostCode());
//        tv_buildingDetail.setText(Project.getInstance().getBuildingDetail());
//        tv_createDate.setText(Project.getInstance().getCreateDate().toString());
//        tv_EditDate.setText(Project.getInstance().getEditDate().toString());
//        tv_latitude.setText(String.valueOf(Project.getInstance().getLattitude()));
//        tv_longitude.setText(String.valueOf(Project.getInstance().getLongitude()));
//        tv_user.setText(Project.getInstance().getUser());
//        tv_lac.setText(Project.getInstance().getLAC());
//        tv_cid.setText(Project.getInstance().getCID());
//
//        // Update Listview
//        sessions.clear();
//
//        for (int i =0;i<Project.getInstance().getSessions().size();i++){
//            sessions.add((Project.getInstance().getSessions().get(i)).getPoint());
//        }
//
//        itemsAdapter.notifyDataSetChanged();
//
//
//    }
//
//    public void Update (){
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_project_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id==R.id.action_edit)
//        {
//            Intent i = new Intent(this,EditProjectActivity.class);
//            startActivity(i);
//        }
//        else if (id==R.id.action_delete){
//            Toast.makeText(this, "Delete Complete!!", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(this,ProjectListActivity.class);
//            startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    Handler myHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    // calling to this function from other pleaces
//                    // The notice call method of doing things
//                    break;
//                default:
//                    TextView name= (TextView)findViewById(R.id.projectDetail_BuildingID);
//                    name.setText(msg.toString());
//                    break;
//            }
//        }
//    };
//}
