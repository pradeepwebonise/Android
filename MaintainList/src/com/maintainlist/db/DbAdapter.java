package com.maintainlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

abstract public class DbAdapter {
	protected static SQLiteDatabase db;
	protected static listDatabaseHelper dbHelper;
	protected String dbName;
	protected String[] dbColumns;
	protected Context context;

	public DbAdapter(Context context) {
		super();
		this.context = context;
		if (unopened())
			DbAdapter.open(context);
	}

	abstract protected void setDbName();

	abstract protected void setDbColumns();

	final public static void open(Context context) throws SQLException {
		dbHelper = new listDatabaseHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.w("LIST",
					"ListsDbAdapter::getWritableDatabase error: "
							+ e.getMessage());
		}
	}

	final public static Boolean unopened() {
		return db == null || !db.isOpen();
	}

	final public static void close() {
		if (!unopened()) {
			dbHelper.close();
		}
	}

	/* Insert records into database */
	public long create(ContentValues initialValues) {
		return db.insert(dbName, null, initialValues);
	}

	/* Update records into database */
	public boolean update(String itemName, ContentValues updateValues) {
		int status;
		itemName = "list_name = '" + itemName + "'";
		status = db.update(dbName, updateValues, itemName, null);
		if (status > 0)
			return true;
		else
			return false;
	}

	/* Delete records from database */
	public boolean delete(String where) {
		int st;
		where = "list_name = '" + where + "'";
		st = db.delete(dbName, where, null);
		if (st > 0)
			return true;
		else
			return false;
	}

	/* fetch all records from database */
	public Cursor fetchAll(String where, String limit) {
		return db.query(dbName, dbColumns, where, null, null, null, limit);
	}

	final public void beginTransaction() {
		db.beginTransaction();
	}

	final public void endTransaction() {
		db.endTransaction();
	}

	final public void succeedTransaction() {
		db.setTransactionSuccessful();
	}
}