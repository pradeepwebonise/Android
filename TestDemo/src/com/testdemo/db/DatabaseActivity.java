package com.testdemo.db;

import android.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class DatabaseActivity extends Activity {
DBAdapter db;
    /** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        db = new DBAdapter(this); 
        System.out.println("bool1");
        insert();
disAll();
        //update();
//dis(3);
//del(5);
    }
private void update(){ 
       
        db.open();
if (db.updateTitle(1, 
         "0470285818", 
"C# 2008 Programmer's Reference",
"Wrox Press"))
            Toast.makeText(this, "Update successful.", 
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", 
                Toast.LENGTH_LONG).show();
        //-------------------
//---retrieve the same title to verify---
        Cursor c = db.getTitle(1);
        if (c.moveToFirst())        
            DisplayTitle(c);
else
            Toast.makeText(this, "No title found", 
             Toast.LENGTH_LONG).show();        
        //-------------------        
db.close();
      
    }
private void insert(){ 
    
        db.open();        
long id;
        id = db.insertTitle(
         "0470285819",
"Teach yourself Java",
"Wrox");        
        id = db.insertTitle(
         "047017661y",         "Professional Windows 07 Gadgets Programming",
"Wrox");
        System.out.println("bool211");
        db.close();
    }
private void disAll(){
    
        db.open();
/*Cursor c = db.getAllTitles();
        if (c.moveToFirst())
        {
            do {          
             System.out.println("bool2");
                DisplayTitle(c);
            } while (c.moveToNext());
        }
        else
         System.out.println("boo3l");*/
try{
Cursor c = db.getAllTitles();
            if (c.moveToFirst())
            {
                do {          
System.out.println("bool2");
                    DisplayTitle(c);
                } while (c.moveToNext());
            }
        }catch(Exception e){
         System.out.println(e);
        }
        db.close();
    }
  
    private void   dis(   int   j){
    
        db.open();
        Cursor c = db.getTitle(j);
        if (c.moveToFirst())        
            DisplayTitle(c);
else
            Toast.makeText(this, "No title found", 
             Toast.LENGTH_LONG).show();
        db.close();
    }        
  
     private void   del(   int   j){
    
        db.open();
if (db.deleteTitle(j))
            Toast.makeText(this, "Delete successful.", 
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", 
                Toast.LENGTH_LONG).show();            
        db.close();        
    }
public void DisplayTitle(Cursor c)
    {
     System.out.println("bool");
        Toast.makeText(this, 
                "id: " + c.getString(0) + "\n" +
                "ISBN: " + c.getString(1) + "\n" +
                "TITLE: " + c.getString(2) + "\n" +
                "PUBLISHER:  " + c.getString(3),
                Toast.LENGTH_LONG).show();        
    } 
}