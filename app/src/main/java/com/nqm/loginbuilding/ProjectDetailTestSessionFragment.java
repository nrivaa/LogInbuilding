package com.nqm.loginbuilding;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ProjectDetailTestSessionFragment extends Fragment {
    private Context ctx;
    private List<String> sessions;
    private ArrayAdapter<String> itemsAdapter;
    private Button btnCreateSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View windows = inflater.inflate(R.layout.activity_project_detail_test_session_fragment, container, false);
        //((TextView)windows.findViewById(R.id.textView)).setText("Windows");
        ctx = windows.getContext();

        // Update Listview
        sessions = new ArrayList<>();

        itemsAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, sessions);

        ListView listView = (ListView) windows.findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);
        btnCreateSession = (Button) windows.findViewById(R.id.btnCS);
        btnCreateSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = Project.getInstance().getBuildingID();
                Intent i = new Intent(ctx, AddSessionActivity.class);
                i.putExtra("BUILDING_ID", id);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ctx, SessionDetailActivity.class);
                i.putExtra("position", position);
                startActivity(i);
                //Log.e("Selected Project",listView.getItemAtPosition(position).toString());
                //Project project = new Project(ProjectListActivity.this);
                //project.GetProjectDetail(listView.getItemAtPosition(position).toString());

            }
        });

        sessions.clear();

        for (int i = 0; i < Project.getInstance().getSessions().size(); i++) {
            sessions.add((Project.getInstance().getSessions().get(i)).getPoint());
        }

        itemsAdapter.notifyDataSetChanged();
        return windows;
    }
}