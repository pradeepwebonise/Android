package com.maintainlist;
import com.maintainlist.db.DbFunctions; 

import com.maintainlist.model.ProjectData;
import com.maintainlist.project.ProjectsDBAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MaintainListActivity extends Activity {
	public ListView listview;
	public Button btn;
	public EditText edTxt;
	ArrayList<String> array_listItems = new ArrayList<String>();;
	ArrayAdapter<String> listAdapter;	  	
	ArrayList<ProjectData> projectData;
	public String str1="Helooo";
	public String textContent;
	DbFunctions dbFunctions;	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.btn_add);
		Log.i("btnnnnnnnnnnnn:",str1);		
		dbFunctions= new DbFunctions(this);
//		btn.setOnClickListener(new View.OnClickListener() {
//		 	@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				edTxt = (EditText) findViewById(R.id.editText_listname);
//				textContent = edTxt.getText().toString();
//				Log.i("text Content====>>:", textContent.toString());
//				if(textContent.equals("")) {}						
//				else
//				{
//					generateListView();
//				}
//				
//				//dbFunctions.storeProjectDataInDB(textContent);	
//				writeToDB(textContent);
//				edTxt.setText("");
//			}			
//		});
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_add:			
			edTxt = (EditText) findViewById(R.id.editText_listname);
			textContent = edTxt.getText().toString();
			Log.i("text Content====>>:", textContent.toString());
			if(textContent.equals("")) {}						
			else
			{
				generateListView();
				//dbFunctions.storeProjectDataInDB(textContent);	
				writeToDB(textContent);
				edTxt.setText("");
			}		
			break;
		case R.id.btn_display:			
			Log.i("Didsplay clicked", "display ..............");
			fetchFromDB();
			break;
	   }
	}			
	public void generateListView()
	{
		 listview = (ListView) findViewById(R.id.my_listview);
		 array_listItems.add(textContent);
		 listAdapter = new ArrayAdapter<String>(MaintainListActivity.this,android.R.layout.simple_list_item_1, array_listItems);				
		 listview.setAdapter(listAdapter);		
	}
	
	public void writeToDB(String data)
	{
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		Log.i("Store Dataaaaaaaaa", "flag2");
		ContentValues values = new ContentValues();
		Log.i("Store Dataaaaaaaaa", "flag3");
		values.put(ProjectsDBAdapter.LIST_NAME, data);
		Log.i("Store Dataaaaaaaaa", "flag4");
		projectAdpt.create(values);
		Log.i("Store Dataaaaaaaaa", "flag5");
	}
	public void fetchFromDB()
	{
		Log.i("fetchfrom db", "display ..............");
		ProjectsDBAdapter projectAdpt = new ProjectsDBAdapter(this);
		
		Log.i("Didsplay clicked dbbbbbbbbbb", "display ..............");
		
		projectData = projectAdpt.getProjectsList();		
		Log.i("Didsplay clicked", "display ........");
	//	ArrayAdapter<String> arrayAdapter =      
		//         new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		
	
	}
	
}	