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

	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);  	/* Display Main activity */
		btn = (Button) findViewById(R.id.btn_add); 	 /* fetching button id  */
		fetchFromDB(); 		/* Fetching Data from database display into listview */
		final ListView lv = (ListView) findViewById(R.id.my_listview);  /* fetching listview id  */
		/* on listview item click  */
		lv.setOnItemClickListener(new OnItemClickListener() {
			/* on click gets listview item id  */ 
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				String selectedFromList = (String) (lv
						.getItemAtPosition(myItemInt));
				/* switch on next activity */
				Intent intent = new Intent(MaintainListActivity.this,
						ListItemDeleteUpdateActivity.class); 
				intent.putExtra("selectedFromList", selectedFromList);
				startActivity(intent);
			}
		});
	}
/*  button click function */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:  /* on add button click  */
			edTxt = (EditText) findViewById(R.id.editText_listname);  /* fetching edittext id */
			textContent = edTxt.getText().toString();
			if (textContent.equals("")) {
			} else {
				writeToDB(textContent);
				fetchFromDB();
				edTxt.setText("");
			}
			break;
		}
	}

	/* Write into database */
	public void writeToDB(String data) {
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		ContentValues values = new ContentValues(); /* Creating context object */
		values.put(ProjectsDBAdapter.LIST_NAME, data);
		projectAdpt.create(values);
	}

	/* Fetching from Database */
	public void fetchFromDB() {
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		projectData = projectAdpt.getProjectsList();
		listview = (ListView) findViewById(R.id.my_listview);
		array_listItems.add(textContent);
		listAdapter = new ArrayAdapter<String>(MaintainListActivity.this,
				android.R.layout.simple_list_item_1, projectData);
		listview.setAdapter(listAdapter);
	}
} /* End of MaintainListActivity class */