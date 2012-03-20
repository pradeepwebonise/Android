package com.maintainlist.project;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.maintainlist.db.DbAdapter;

public class ProjectsDBAdapter extends DbAdapter {

	public ProjectsDBAdapter(Context context) {
		super(context);
		Log.i("Dbbbbbb Adapater Dataaaaaaaaa", "flag000000");
		setDbName();
		setDbColumns();
	}

	public String ROWID = "_id";
	public final static String LIST_NAME = "list_name";

	@Override
	protected void setDbColumns() {
		this.dbColumns = new String[] { "_id", LIST_NAME };
	}

	@Override
	protected void setDbName() {
		this.dbName = "maintain_list";
	}

	public long create(ContentValues values) {
		return super.create(values);
	}

	public boolean delete(String selectedFromList) 
	{
		Log.i("delete fun from PrjectDBAdapter.......: ", selectedFromList.toString());
		return super.delete(selectedFromList);
	}
	
	public boolean update(String itemName, ContentValues values) {
		return super.update(itemName, values);
	}

	public ArrayList<String> getProjectsList() {
		Cursor projectC = this.fetchAll(null, null);
		ArrayList<String> mArrayList = new ArrayList<String>();
		Log.i("sizeeeee", String.valueOf(projectC.getCount()));
		projectC.moveToFirst();
		while (!projectC.isAfterLast()) {
			mArrayList.add(projectC.getString(projectC
					.getColumnIndex(ProjectsDBAdapter.LIST_NAME)));
			projectC.moveToNext();
		}
		return mArrayList;
	}

	public void deleteAll() {
		try {
			db.beginTransaction();
			this.delete();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

	}
}
