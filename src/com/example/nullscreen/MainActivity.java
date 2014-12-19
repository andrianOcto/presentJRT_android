package com.example.nullscreen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Socket socket;
    ImageView imageView ;
    Bitmap bitmap=null;
    private static final int SERVERPORT = 10888;
    public String IP="";
    public static boolean runnable=false;
    DataInputStream dis;
    ByteArrayOutputStream fos;
    byte[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
    	//menjalankan thread
        
        Intent i=getIntent();
        IP=i.getExtras().getString("IP");
    	new Thread(new ClientThread()).start();
    	runnable=true;
        imageView = (ImageView) findViewById(R.id.imageView1);

        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        runnable=false;
    }
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try {
			if(socket.isConnected())
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    runnable=true;
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

	
    class ClientThread implements Runnable {
    	 
        @Override
        public void run() {
        	 DataInputStream dataInputStream = null;
            try {
            	while(runnable)
            	{
            		Log.d("test", "masuk "+IP);
            		Socket s = new Socket(IP, 10888);
            		dis = new DataInputStream(
                            s.getInputStream());
            		fos = new ByteArrayOutputStream();
            		long arrlen = dis.readLong();
            		for (long i = 0; i < arrlen; i++) {
            	        fos.write(dis.read());
            	    }
            		fos.flush();
            		
            		
            		
//            		Log.d("thread","jalan");
//            		socket = new Socket(IP, 10888);
//				   dataInputStream = new DataInputStream(socket.getInputStream());
//				   // dataOutputStream.writeUTF(textOut.getText().toString());
//				   final String base64Code = dataInputStream.readUTF();
//
//				   Log.d("String", ":" + base64Code);
//
				   //Thread update UI
				   runOnUiThread(new Runnable(){
				        public void run() {
				        	Log.d("update Image", "update image");
				        	data=fos.toByteArray();
				        	Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
				        	
				        	imageView.setImageBitmap(bitmap);
				        }
				    });
				   
            	}
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
 
        }
 
    }
}
