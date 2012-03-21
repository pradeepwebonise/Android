package com.maintainlist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.maintainlist.list.ListsDBAdapter;

public class ListItemDeleteUpdateActivity extends Activity {
	MaintainListActivity obj_maintainlistact;
	TextView textContent;
	EditText edtTextUpdate;
	String selectedFromList;

	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		selectedFromList = (String) i.getSerializableExtra("selectedFromList");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_delete_update);
		textContent = (TextView) findViewById(R.id.txt_deleteContent); /*
																		 * fetch
																		 * text
																		 * content
																		 * id
																		 */
		textContent.setText(selectedFromList); /*
												 * set text content by selected
												 * item
												 */
		edtTextUpdate = (EditText) findViewById(R.id.editText_update); /*
																		 * fetch
																		 * edit
																		 * text
																		 * content
																		 * id
																		 */
		edtTextUpdate.setText(selectedFromList); /*
												 * set edit text content by
												 * selected item
												 */
	}

	/* button click function */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_delete: /* clicked delete button */
			itemDelete();
			break;
		case R.id.btn_cancel: /* clicked cancel button */
			finish();
			break;
		case R.id.btn_update: /* clicked update button */
			itemUpdate();
			break;
		}
	}

	/* List view item update and switch to previous 'MaintainListActivity' */
	private void itemUpdate() {
		// TODO Auto-generated method stub
		Boolean delFlag;
		edtTextUpdate = (EditText) findViewById(R.id.editText_update);
		String selectedFromList_tmp = edtTextUpdate.getText().toString();
		ListsDBAdapter projectadpt = new ListsDBAdapter(this);
		ContentValues values = new ContentValues();
		values.put(ListsDBAdapter.LIST_NAME, selectedFromList_tmp);
		delFlag = projectadpt.update(selectedFromList, values);
		if (delFlag == true) {
			Log.i("Updated", selectedFromList);
			Intent intent = new Intent(ListItemDeleteUpdateActivity.this,
					MaintainListActivity.class);
			startActivity(intent);
		} else {
			Log.i("Not Updated ", selectedFromList);
			finish();
		}
	}

	/* List view item delete and switch to previous 'MaintainListActivity' */
	private void itemDelete() {
		// TODO Auto-generated method stub
		Boolean delFlag;
		ListsDBAdapter projectadpt = new ListsDBAdapter(this);
		delFlag = projectadpt.delete(selectedFromList);
		if (delFlag == true) {
			Log.i("Deleted", selectedFromList);
			Intent intent = new Intent(ListItemDeleteUpdateActivity.this,
					MaintainListActivity.class); /* Switch MainActivity */
			startActivity(intent);
		} else {
			Log.i("Not Deleted ", selectedFromList);
			finish();
		}
	}
} /* End of ListItemDeleteUpdateActivity class */
