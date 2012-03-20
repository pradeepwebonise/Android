package com.maintainlist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.maintainlist.db.DbFunctions;
import com.maintainlist.project.ProjectsDBAdapter;

public class MaintainListActivity extends Activity {
	public ListView listview;
	public Button btn;
	public EditText edTxt;
	ArrayList<String> array_listItems = new ArrayList<String>();;
	ArrayAdapter<String> listAdapter;
	ArrayList<String> projectData;
	public String str1 = "Helooo";
	public String textContent;
	DbFunctions dbFunctions;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.btn_add);
		Log.i("btnnnnnnnnnnnn:", str1);
		dbFunctions = new DbFunctions(this);
		fetchFromDB();
		final ListView lv = (ListView) findViewById(R.id.my_listview);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				String selectedFromList = (String) (lv
						.getItemAtPosition(myItemInt));
				Log.i("list view id .....:: ", selectedFromList);
				
				 Intent intent = new Intent(MaintainListActivity.this, ListItemDeleteActivity.class);
				 intent.putExtra("selectedFromList", selectedFromList);
				 startActivity(intent);
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:
			edTxt = (EditText) findViewById(R.id.editText_listname);
			textContent = edTxt.getText().toString();
			Log.i("text Content====>>:", textContent.toString());
			if (textContent.equals("")) {
			} else {
				writeToDB(textContent);
				fetchFromDB();
				edTxt.setText("");
			}
			break;
		}
	}

	/* Generate list view */
	public void generateListView() {
		listview = (ListView) findViewById(R.id.my_listview);
		array_listItems.add(textContent);
		listAdapter = new ArrayAdapter<String>(MaintainListActivity.this,
				android.R.layout.simple_list_item_1, array_listItems);
		listview.setAdapter(listAdapter);
	}

	/* Write into database */
	public void writeToDB(String data) {
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		// Log.i("Store Dataaaaaaaaa", "flag2");
		ContentValues values = new ContentValues();
		// Log.i("Store Dataaaaaaaaa", "flag3");
		values.put(ProjectsDBAdapter.LIST_NAME, data);
		// Log.i("Store Dataaaaaaaaa", "flag4");
		projectAdpt.create(values);
		// Log.i("Store Dataaaaaaaaa", "flag5");
	}

	/* Fetching from Database */
	public void fetchFromDB() {
		// Log.i("fetchfrom db", "display ..............");
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		// Log.i("Didsplay clicked dbbbbbbbbbb", "display ..............");
		projectData = projectAdpt.getProjectsList();
		for (int i = 0; i < projectData.size(); i++) {
			Log.i("Didsplay clicked", projectData.get(i));
		}
		listview = (ListView) findViewById(R.id.my_listview);
		array_listItems.add(textContent);
		listAdapter = new ArrayAdapter<String>(MaintainListActivity.this,
				android.R.layout.simple_list_item_1, projectData);
		listview.setAdapter(listAdapter);
	}
} /* End of MaintainListActivity class */