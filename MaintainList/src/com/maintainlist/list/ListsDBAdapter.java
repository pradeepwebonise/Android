package com.maintainlist.list;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.maintainlist.db.DbAdapter;

public class ListsDBAdapter extends DbAdapter {

	public ListsDBAdapter(Context context) {
		super(context);
		setDbName();
		setDbColumns();
	}

	public String ROWID = "_id";
	public final static String LIST_NAME = "list_name";

	/* Set database columns */
	@Override
	protected void setDbColumns() {
		this.dbColumns = new String[] { "_id", LIST_NAME };
	}

	/* Set database name */
	@Override
	protected void setDbName() {
		this.dbName = "maintain_list";
	}

	public long create(ContentValues values) {
		return super.create(values);
	}

	public boolean delete(String selectedFromList) {
		return super.delete(selectedFromList);
	}

	public boolean update(String itemName, ContentValues values) {
		return super.update(itemName, values);
	}

	public ArrayList<String> getProjectsList() {
		Cursor projectC = this.fetchAll(null, null);
		ArrayList<String> mArrayList = new ArrayList<String>();
		projectC.moveToFirst();
		while (!projectC.isAfterLast()) {
			mArrayList.add(projectC.getString(projectC
					.getColumnIndex(ListsDBAdapter.LIST_NAME)));
			projectC.moveToNext();
		}
		return mArrayList;
	}
}
