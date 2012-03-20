package com.maintainlist;

import com.maintainlist.project.ProjectsDBAdapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ListItemDeleteActivity extends Activity {
	MaintainListActivity obj_maintainlistact;
	TextView textContent;
	EditText edtTextUpdate;
	String selectedFromList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		selectedFromList = (String) i.getSerializableExtra("selectedFromList");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_delete);
		textContent = (TextView) findViewById(R.id.txt_deleteContent);
		textContent.setText(selectedFromList);
		edtTextUpdate = (EditText) findViewById(R.id.editText_update);
		edtTextUpdate.setText(selectedFromList);
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
		case R.id.btn_update:
			itemUpdate();
			break;
		}
	}

	private void itemUpdate() {
		// TODO Auto-generated method stub
		Boolean delFlag;
		edtTextUpdate = (EditText) findViewById(R.id.editText_update);
		String selectedFromList_tmp = edtTextUpdate.getText().toString();
		ProjectsDBAdapter projectadpt = new ProjectsDBAdapter(this);
		
		ContentValues values = new ContentValues();
		// Log.i("Store Dataaaaaaaaa", "flag3");
		//selectedFromList = "'"+selectedFromList_tmp+"'";
		values.put(ProjectsDBAdapter.LIST_NAME, selectedFromList_tmp);
		
		Log.i("Store Dataaaaaaaaa", values.toString());
		delFlag = projectadpt.update(selectedFromList, values);
		
		
		//delFlag = projectadpt.update(selectedFromList, selectedFromList_tmp);
		
		
		if(delFlag==true) {
			Log.i("Updated", selectedFromList);
			Intent intent = new Intent(ListItemDeleteActivity.this, MaintainListActivity.class);
			 startActivity(intent);
		}
		else
			Log.i("Not Updated ", selectedFromList);
		
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
