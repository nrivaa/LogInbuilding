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
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class CreateProjectActivity extends ActionBarActivity {

    private String[] arraySpinner;
    private TextView tvBuildingID,tvName,tvContact,tvNumber,tvImsiA,tvImsiD,tvImsiT,tvImsi3,tvAddress,tvTambon,tvDistrict,tvPostCode,tvDetail;
    private ImageButton btnCapture;
    private RadioGroup rgService, rgAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        this.arraySpinner = new String[] {
                "Krabi",
                "Bangkok",
                "Kanchanaburi",
                "Kalasin",
                "Kamphaeng Phet",
                "Khon Kaen",
                "Chanthaburi",
                "Chachoengsao",
                "Chiang Mai",
                "Chiang Rai",
                "Chon Buri",
                "Chai Nat",
                "Chaiyaphum",
                "Chumphon",
                "Trang",
                "Trat",
                "Tak",
                "Nakhon Nayok",
                "Nakhon Pathom",
                "Nakhon Phanom",
                "Nakhon Ratchasima",
                "Nakhon Si Thammarat",
                "Nakhon Sawan",
                "Nonthaburi",
                "Narathiwat",
                "Nan",
                "Buriram",
                "Pathum Thani",
                "Prachuap Khiri Khan",
                "Prachin Buri",
                "Pattani",
                "Phetchaburi",
                "Phetchabun",
                "Phrae",
                "Phayao",
                "Phangnga",
                "Phatthalung",
                "Phichit",
                "Phitsanulok",
                "Phuket",
                "Mae Hong Son",
                "Maha Sarakham",
                "Mukdahan",
                "Yasothon",
                "Yala",
                "Roi Et",
                "Ranong",
                "Rayong",
                "Ratchaburi",
                "Lop Buri",
                "Lampang",
                "Loei",
                "Lamphun",
                "Sisaket",
                "Sakon Nakhon",
                "Songkhla",
                "Satun",
                "Samut Prakan",
                "Samut Songkhram",
                "Samut Sakhon",
                "Sa Kaeo",
                "Sara Buri",
                "Sing Buri",
                "Sukhothai",
                "Suphan Buri",
                "Surat Thani",
                "Surin",
                "Nong Khai",
                "Nong Bua Lamphu",
                "Ang Thong",
                "Amnat Charoen",
                "Udon Thani",
                "Uttaradit",
                "Uthai Thani",
                "Ubon Ratchathani",
                "Ayutthaya",
                "Thonburi"
        };
        Spinner s = (Spinner) findViewById(R.id.spnProvince);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        final Context ctx = getApplicationContext();

        tvBuildingID = (TextView)findViewById(R.id.createProject_BuildingID);
        tvName = (TextView)findViewById(R.id.createProject_BuildingName);
        tvNumber = (TextView)findViewById(R.id.createProject_contactName);

        Button btn_submit = (Button)findViewById(R.id.projectCreate_btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ProjectDetailActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_project, menu);
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
