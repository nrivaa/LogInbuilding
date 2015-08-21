package com.nqm.loginbuilding;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class AddSessionActivity extends ActionBarActivity {

    String id;
    private EditText tvPoint, tvFloor, tvDesc;
    private RadioGroup rgLoc, rgRemark;
    private String[] arraySpinner, arraySpinner2;
    private Spinner s0, s1, s2, s3, s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        final Context ctx = getApplicationContext();

        tvPoint = (EditText)findViewById(R.id.createSession_point);
        tvFloor = (EditText)findViewById(R.id.createSession_floor);
        tvDesc = (EditText)findViewById(R.id.createSession_description);
        rgLoc = (RadioGroup)findViewById(R.id.rbLoc);
        rgRemark = (RadioGroup)findViewById(R.id.rbRemark);

        this.arraySpinner = new String[] {
                "Normal",
                "Connect_Failed",
                "Login_Failed",
                "No_Coverage"
        };
        this.arraySpinner2 = new String [] {
                "Test 1",
                "Test 2"
        };
        s0 = (Spinner) findViewById(R.id.spnLocation);
        s1 = (Spinner) findViewById(R.id.spnStatusAwn);
        s2 = (Spinner) findViewById(R.id.spnStatusDtac);
        s3 = (Spinner) findViewById(R.id.spnStatusTrue);
        s4 = (Spinner) findViewById(R.id.spnStatus3BB);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner2);
        s1.setAdapter(adapter1);
        s2.setAdapter(adapter1);
        s3.setAdapter(adapter1);
        s4.setAdapter(adapter1);
        s0.setAdapter(adapter0);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id= null;
            } else {
                id= extras.getString("BUILDING_ID");
            }
        } else {
            id= (String) savedInstanceState.getSerializable("BUILDING_ID");
        }

        Button btn_submit = (Button)findViewById(R.id.addSession_btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubmitSession(true);

            }
        });
    }

    // SubmitProject
    private void SubmitSession(boolean submitFlag) {
        if (submitFlag) {
            Session ss = new Session();
            ss.setBuildingID(Project.getInstance().getBuildingID());
            ss.setPoint(tvPoint.getText().toString());
            ss.setFloor(tvFloor.getText().toString());
            ss.setDescription(tvDesc.getText().toString());
            ss.setLocation(s0.getSelectedItem().toString());
            //get text form radio button
            int selectedId = rgLoc.getCheckedRadioButtonId();
            RadioButton radioBtn = (RadioButton) findViewById(selectedId);
            ss.setLoc_Specification(radioBtn.getText().toString());
            ss.setStatus_AWN(s1.getSelectedItem().toString());
            ss.setStatus_DTAC(s2.getSelectedItem().toString());
            ss.setStatus_TRUEH(s3.getSelectedItem().toString());
            ss.setStatus_3BB(s4.getSelectedItem().toString());
            //get text form radio button
            int selectedId1 = rgRemark.getCheckedRadioButtonId();
            RadioButton radioBtn1 = (RadioButton) findViewById(selectedId1);
            ss.setRemark(radioBtn1.getText().toString());
            ss.setCreateDate("Now()");
            ss.setConfirmStatus("0");
            ss.setConfirmDate("Now()");
            ss.setUser("noppadon");

            ss.AddSession();

        }else{
            Toast.makeText(getApplicationContext(),
                    "Sorry! submitFlag False",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
