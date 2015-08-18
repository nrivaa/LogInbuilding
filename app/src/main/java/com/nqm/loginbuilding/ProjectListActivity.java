package com.nqm.loginbuilding;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ProjectListActivity extends ActionBarActivity {

    private ListView listView;
    private EditText tb_search;
    private ArrayAdapter<String> itemsAdapter;
    private List<String> items;
    private Button btnCreateProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        GetProjectListAsync task = new GetProjectListAsync(ProjectListActivity.this);
        task.execute();

        // List view
        items = new ArrayList<>();
        listView = (ListView) findViewById(R.id.ProjectList_ListView);
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("Selected Project",listView.getItemAtPosition(position).toString());
                //Project project = new Project(ProjectListActivity.this);
                Project.getInstance().setCtx(ProjectListActivity.this);
                Project.getInstance().GetProjectDetail(listView.getItemAtPosition(position).toString());

            }
        });

        tb_search = (EditText)findViewById(R.id.ProjectList_Search);
        tb_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProjectListActivity.this.itemsAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnCreateProject = (Button)findViewById(R.id.btnCP);
        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ProjectListActivity.this,CreateProjectActivity.class);
                startActivity(i);
            }
        });

    }

    public void UpdateProjectList(List<String> projectList){
        items.clear();
        items.addAll(projectList);
        itemsAdapter.notifyDataSetChanged();
        Log.e("Update Listview","Success");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logOut) {
            Intent i = new Intent(ProjectListActivity.this, LoginActivity.class);
            i.putExtra("logoutFlag", true);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
