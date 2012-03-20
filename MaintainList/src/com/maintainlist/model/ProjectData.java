package com.maintainlist.model;

import android.database.Cursor;

import com.maintainlist.project.ProjectsDBAdapter;

public class ProjectData {

	String listName;
	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
	public ProjectData() {
		super();
	}

	public ProjectData(Cursor c) {

		super();
		if (c.moveToFirst()) {			
			this.listName = c.getString(c
					.getColumnIndex(ProjectsDBAdapter.LIST_NAME));
		}
	}
}
