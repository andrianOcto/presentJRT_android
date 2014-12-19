package com.example.nullscreen;

import com.example.nullscreen.MainActivity.ClientThread;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreviewActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button = (Button) findViewById(R.id.button1);
		final Intent i=new Intent(this,MainActivity.class);
        //event listener button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String IP;
            	EditText mEdit   = (EditText)findViewById(R.id.editText1);
                IP = mEdit.getText().toString();
                if(IP.equals(""))
                {
                	Toast toast = Toast.makeText(getApplicationContext(), "IP masih kosong", Toast.LENGTH_SHORT);
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
