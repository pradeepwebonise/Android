package com.maintainlist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class listDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "dblist";
	private static final int DATABASE_VERSION = 1;

	public listDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			// Android requires _id
			String createSql = "CREATE TABLE maintain_list (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "list_name TEXT );";

			Log.v("LIST", "########Creating project_resources: " + createSql);
			db.execSQL(createSql);
			createSql = null;

		} catch (Exception e) {
			Log.e("LIST", "##############db creation failed: " + e.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LIST", "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS maintain_list");
		onCreate(db);
	}
}