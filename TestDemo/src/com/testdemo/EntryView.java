package com.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class EntryView extends Activity 
{	
	TestDemoActivity obj_testdemo;
	TextView text_content;
	@Override
	 public void onCreate(Bundle savedInstanceState)
    {
		Intent i = getIntent();
		String entry_content = (String)i.getSerializableExtra("entry_content");
 		//String text_val = obj_testdemo.entry_content;
		//String text_val="ppppppppppppppppp";
		Log.i("New Acttttttttyyyy Textvvvvvvvlllll:",entry_content);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelayout);      
        text_content = (TextView) findViewById(R.id.text_content);
        text_content.setText(entry_content);       		
    }
}
