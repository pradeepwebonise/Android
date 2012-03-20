package com.maintainlist.project;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.maintainlist.db.DbAdapter;

public class ProjectsDBAdapter extends DbAdapter {

	public ProjectsDBAdapter(Context context) {
		super(context);
		setDbName();
		setDbColumns();
	}

	public String ROWID = "_id";
	public final static String LIST_NAME = "list_name";

	// setting database columns
	@Override
	protected void setDbColumns() {
		this.dbColumns = new String[] { "_id", LIST_NAME };
	}

	// Setting database name
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

	/* fetching data from database and add it into arraylist */
	public ArrayList<String> getProjectsList() {
		Cursor projectC = this.fetchAll(null, null); /*
													 * Fetching data from
													 * database store into
													 * cursor
													 */
		ArrayList<String> mArrayList = new ArrayList<String>();
		projectC.moveToFirst();
		/* fetching data from cursor and stored it into arraylist */
		while (!projectC.isAfterLast()) {
			mArrayList.add(projectC.getString(projectC
					.getColumnIndex(ProjectsDBAdapter.LIST_NAME)));
			projectC.moveToNext();
		}
		return mArrayList;
	}
} /* End of ProjectsDBAdapter class */
