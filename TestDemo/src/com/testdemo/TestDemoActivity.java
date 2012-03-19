package com.testdemo;
import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestDemoActivity extends Activity {
    /** Called when the activity is first created. */
	
	public EditText entry;  // object for textfield
	public String entry_content;
	public String getEntry_content() {
		return entry_content;
	}
	public void setEntry_content(String entry_content) {
		this.entry_content = entry_content;
	}
	public EditText getEntry() {
		return entry;
	}
	public void setEntry(EditText entry) {
		this.entry = entry;
	}
	public Button ok;  // object for ok button
	public Button cancel;  // object for cancel button
    // create method which loads first  
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayout);   // display activity 
        entry = (EditText) findViewById(R.id.entry);  // access id of entry textbox  
        ok = (Button) findViewById(R.id.ok);    // access id of ok button 
        cancel = (Button) findViewById(R.id.cancel);  // access id of cancel button 
        final String test="tttt";
        Log.i("btnnnnnnnnnnnn:", test);
        // entry button event
        ok.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Log.i("clickeddddddddddd",test);
				 entry_content = entry.getText().toString();
				 Log.i("clickeddddddddddd",entry_content);
				 Intent intent = new Intent(TestDemoActivity.this, EntryView.class);
				 intent.putExtra("entry_content", entry_content);
				 startActivity(intent);
				 //finish();
			}
		});
        // cancel button event
        cancel.setOnClickListener(new View.OnClickListener() {			
		 	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});       
    }
}