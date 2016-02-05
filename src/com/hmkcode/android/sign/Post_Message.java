package com.hmkcode.android.sign;



import java.util.HashMap;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Post_Message extends Activity {

	protected static final String DEFAULT = null;
	EditText sub , cont;
	Button publish;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post__message);
		publish = (Button) findViewById(R.id.Publish);
		sub = (EditText) findViewById(R.id.subject);
		cont =(EditText) findViewById(R.id.content);
	}


	
	
	
	
	public void send(View v) 
	{
		
		
		
		final String subject_android = sub.getText().toString();
		final String  cotend_android = cont.getText().toString();
		
		SharedPreferences q = getSharedPreferences("mydata", Context.MODE_PRIVATE);
   	 SharedPreferences.Editor edit;
   	final String from_android = q.getString("From", DEFAULT);
    Toast.makeText(Post_Message.this,from_android + subject_android + cotend_android ,Toast.LENGTH_LONG).show();
   	
		class post extends AsyncTask<Void,Void,String>{
			 
            ProgressDialog loading;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Post_Message.this,"Publishing Post...","Wait...",false,false);
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Post_Message.this,s,Toast.LENGTH_LONG).show();
            }
 
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.POST_FROM,from_android);
                params.put(Config.POST_CONTENT,cotend_android);
                
                params.put(Config.POST_SUBJECT,subject_android);
        
             
 
                RequestHandler2 r = new RequestHandler2();
                String res = r.sendPostRequest(Config.URL_POST, params);
                return res;
            }
        }//$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		
		 post ae = new post();
	        ae.execute();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
