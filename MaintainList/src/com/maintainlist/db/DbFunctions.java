package com.maintainlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.maintainlist.project.ProjectsDBAdapter;

public class DbFunctions {

	private Context context;

	public DbFunctions(Context context) {
		super();
	}

	// //// Store data in project_resource model
	public void storeProjectDataInDB(String listData) {
		Log.i("Store Dataaaaaaaaa", "flag1");
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(context);
		// projectAdpt.deleteAll();
		Log.i("Store Dataaaaaaaaa", "flag2");
		ContentValues values = new ContentValues();
		Log.i("Store Dataaaaaaaaa", "flag3");
		values.put(ProjectsDBAdapter.LIST_NAME, listData);
		Log.i("Store Dataaaaaaaaa", "flag4");
		projectAdpt.create(values);
		Log.i("Store Dataaaaaaaaa", "flag5");

	}

}