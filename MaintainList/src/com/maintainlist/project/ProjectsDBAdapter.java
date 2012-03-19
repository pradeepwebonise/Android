package com.maintainlist.project;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.maintainlist.db.DbAdapter;
import com.maintainlist.model.ProjectData;

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
		this.dbColumns = new String[] { "_id", LIST_NAME  };
	}
	
	@Override
	protected void setDbName() {
		this.dbName = "maintain_list";
	}

	public long create(ContentValues values) {
		return super.create(values);
	}

	public boolean update(long rowId, ContentValues values) {
		return super.update(rowId, values);
	}

	public ArrayList<ProjectData> getProjectsList() {
		Cursor projectC = this.fetchAll(null, null);
		ArrayList<ProjectData> projectList = new ArrayList<ProjectData>();
		Log.i("sizeeeee",String.valueOf(projectC.getCount()));
		while (projectC.moveToNext()) {
			ProjectData proj_data = new ProjectData(projectC);
			projectList.add(proj_data);
		}
		return projectList;
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
