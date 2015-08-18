package com.nqm.loginbuilding;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class SessionDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_detail);

        // Get Extras
        Intent i = getIntent();
        int position = i.getIntExtra("position",0);


        final Context ctx = getApplicationContext();

        Button btn_confirm = (Button)findViewById(R.id.sessionDetail_btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ProjectDetailActivity.class);
                startActivity(i);
            }
        });

        SetControl(position);
    }

    private TextView tv_point;
    private TextView tv_floor;
    private TextView tv_description;
    private TextView tv_location;
    private TextView tv_loc_spec;
    private TextView tv_status_awn;
    private TextView tv_status_dtac;
    private TextView tv_status_trueh;
    private TextView tv_status_3bb;
    private TextView tv_remark;
    private TextView tv_createdate;
    private TextView tv_confirmStatus;
    private TextView tv_confirmDate;
    private TextView tv_user;

    private void SetControl(int position){
        // Locate control
        tv_point=(TextView)findViewById(R.id.sessionDetail_tv_point);
        tv_floor=(TextView)findViewById(R.id.sessionDetail_tv_floor);
        tv_description=(TextView)findViewById(R.id.sessionDetail_tv_description);
        tv_location=(TextView)findViewById(R.id.sessionDetail_tv_location);
        tv_loc_spec=(TextView)findViewById(R.id.sessionDetail_tv_loc_specification);
        tv_status_awn=(TextView)findViewById(R.id.sessionDetail_tv_status_awn);
        tv_status_dtac=(TextView)findViewById(R.id.sessionDetail_tv_status_dtac);
        tv_status_trueh=(TextView)findViewById(R.id.sessionDetail_tv_status_trueh);
        tv_status_3bb=(TextView)findViewById(R.id.sessionDetail_tv_status_3bb);
        tv_remark=(TextView)findViewById(R.id.sessionDetail_tv_remark);
        tv_createdate=(TextView)findViewById(R.id.sessionDetail_tv_createdate);
        tv_confirmStatus=(TextView)findViewById(R.id.sessionDetail_tv_confirm_status);
        tv_confirmDate=(TextView)findViewById(R.id.sessionDetail_tv_confirm_date);
        tv_user=(TextView)findViewById(R.id.sessionDetail_tv_user);


        // Assign value to control
        tv_point.setText(Project.getInstance().getSessions().get(position).getPoint());
        tv_floor.setText(Project.getInstance().getSessions().get(position).getFloor());
        tv_description.setText(Project.getInstance().getSessions().get(position).getDescription());
        tv_location.setText(Project.getInstance().getSessions().get(position).getLocation());
        tv_loc_spec.setText(Project.getInstance().getSessions().get(position).getLoc_Specification());
        tv_status_awn.setText(Project.getInstance().getSessions().get(position).getStatus_AWN());
        tv_status_dtac.setText(Project.getInstance().getSessions().get(position).getStatus_DTAC());
        tv_status_trueh.setText(Project.getInstance().getSessions().get(position).getStatus_TRUEH());
        tv_status_3bb.setText(Project.getInstance().getSessions().get(position).getStatus_3BB());
        tv_remark.setText(Project.getInstance().getSessions().get(position).getRemark());
        tv_createdate.setText(Project.getInstance().getSessions().get(position).getCreateDate().toString());
        tv_confirmStatus.setText(Project.getInstance().getSessions().get(position).getConfirmStatus());
        tv_confirmDate.setText(Project.getInstance().getSessions().get(position).getConfirmStatus());
        tv_user.setText(Project.getInstance().getSessions().get(position).getUser());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id==R.id.action_edit){
            Intent i = new Intent(this,EditSessionActivity.class);
            startActivity(i);
        }
        else if (id==R.id.action_delete){
            Toast.makeText(this, "Delete Complete!!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,ProjectDetailActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
