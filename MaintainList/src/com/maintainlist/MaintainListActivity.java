package com.maintainlist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.maintainlist.list.ListsDBAdapter;

public class MaintainListActivity extends Activity {
	public ListView listview;
	public Button btn;
	public EditText edTxt;
	public Context context;
	ArrayList<String> array_listItems = new ArrayList<String>();;
	ArrayAdapter<String> listAdapter;
	ArrayList<String> listData;
	public String textContent;

	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); /* Display Main activity */

		fetchFromDB(); /* Fetching Data from database display into listview */
		final ListView lv = (ListView) findViewById(R.id.my_listview); /*
																		 * fetching
																		 * listview
																		 * id
																		 */
		/* on list view item click */
		lv.setOnItemClickListener(new OnItemClickListener() {
			/* on click gets list view item id */
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				String selectedFromList = (String) (lv
						.getItemAtPosition(myItemInt));
				/* switch on next 'ListItemDeleteUpdateActivity' activity */
				Intent intent = new Intent(MaintainListActivity.this,
						ListItemDeleteUpdateActivity.class);
				intent.putExtra("selectedFromList", selectedFromList);
				startActivity(intent);
			}
		});
	}

	/* button click function */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add: /* on add button click */
			edTxt = (EditText) findViewById(R.id.editText_listname); /*
																	 * fetching
																	 * edit text
																	 * id
																	 */
			textContent = edTxt.getText().toString();
			/* Checking edit text empty or nor */
			if (textContent.equals("")) {
			} else { /*
					 * edit text is not empty then write into database and show
					 * edit text content into database
					 */
				writeToDB(textContent);
				fetchFromDB();
				edTxt.setText("");
			}
			break;
		}
	}

	/* Write edit text content into database */
	public void writeToDB(String data) {
		ListsDBAdapter projectAdpt = new ListsDBAdapter(this);
		ContentValues values = new ContentValues(); /* Creating context object */
		values.put(ListsDBAdapter.LIST_NAME, data);
		projectAdpt.create(values);
	}

	/* Fetching list data from Database and display into list view */
	public void fetchFromDB() {
		ListsDBAdapter projectAdpt = new ListsDBAdapter(this);
		listData = projectAdpt.getProjectsList();
		listview = (ListView) findViewById(R.id.my_listview);
		array_listItems.add(textContent);
		listAdapter = new ArrayAdapter<String>(MaintainListActivity.this,
				android.R.layout.simple_list_item_1, listData);
		listview.setAdapter(listAdapter);
	}

} /* End of MaintainListActivity class */