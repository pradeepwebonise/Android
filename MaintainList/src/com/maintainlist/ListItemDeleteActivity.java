package com.maintainlist;

import com.maintainlist.project.ProjectsDBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ListItemDeleteActivity extends Activity {
	MaintainListActivity obj_maintainlistact;
	TextView text_content;
	String selectedFromList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		selectedFromList = (String) i.getSerializableExtra("selectedFromList");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_delete);
		text_content = (TextView) findViewById(R.id.txt_deleteContent);
		text_content.setText(selectedFromList);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_delete:
			itemDelete();
			break;
		case R.id.btn_cancel:
			finish();
			break;
		}
	}

	private void itemDelete() {
		// TODO Auto-generated method stub
		Boolean delFlag;
		ProjectsDBAdapter projectadpt = new ProjectsDBAdapter(this);
		delFlag = projectadpt.delete(selectedFromList);
		if(delFlag==true) {
			Log.i("Deleted", selectedFromList);
			Intent intent = new Intent(ListItemDeleteActivity.this, MaintainListActivity.class);
			 startActivity(intent);
		}
		else
			Log.i("Not Deleted ", selectedFromList);
	}

}
