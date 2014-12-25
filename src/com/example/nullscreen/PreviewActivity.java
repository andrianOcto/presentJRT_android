package com.example.nullscreen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.nullscreen.MainActivity.ClientThread;

import android.net.*;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreviewActivity extends ActionBarActivity {
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button = (Button) findViewById(R.id.button1);
		final Intent i=new Intent(this,MainActivity.class);
        //event listener button
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		
        button.setOnClickListener(new View.OnClickListener() {
        	
      	  
            @SuppressLint("NewApi")
			public void onClick(View v) {
            	String IP;
            	EditText mEdit   = (EditText)findViewById(R.id.editText1);

                IP = mEdit.getText().toString();
            	matcher = pattern.matcher(IP);
                if(IP.equals("") || IP.isEmpty())
                {
                	Toast.makeText(getApplicationContext(), "IP address is empty, please fill it first", Toast.LENGTH_SHORT).show();
                }
                else if(!matcher.matches())
                {
                	Toast.makeText(getApplicationContext(), "IP address is not vadlid", Toast.LENGTH_SHORT).show();
                }
                else
                {
                	i.putExtra("IP", IP);
                	startActivity(i);
                	
                }
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
