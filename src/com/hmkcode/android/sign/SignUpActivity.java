package com.hmkcode.android.sign;


 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends Activity  {
	 private EditText myNameNew;
	  private EditText myRollNew;
	  private EditText myemailNew;
	  private EditText mybranchNew;
	  private EditText mypassNew;
	  Button mySignUp;
	  

		
	String desitation_android;
	 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        

       
        
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Student", "Class Representative", "Faculty"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        
        myNameNew= (EditText) findViewById(R.id.UserNameNew);
        myRollNew = (EditText) findViewById(R.id.RollNew);
        myemailNew = (EditText) findViewById(R.id.EmailNew);
        mybranchNew = (EditText) findViewById(R.id.BranchNew);
        mypassNew = (EditText) findViewById(R.id.PassNew);
        mySignUp = (Button) findViewById(R.id.signupNew);
        
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {       	 
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                    int position, long id) {
                // On selecting a spinner item
            	desitation_android = adapter.getItemAtPosition(position).toString();               
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });}
        
			
	public void insert(View v) {
		// TODO Auto-generated method stub
		final String	name_android= myNameNew.getText().toString();
		final String	roll_android = myRollNew.getText().toString();
		final String	branch_android= mybranchNew.getText().toString();
		final String	pass_android = mypassNew.getText().toString();
		final String	email_android = myemailNew.getText().toString();
		
		
		class AddEmployee extends AsyncTask<Void,Void,String>{
			 
            ProgressDialog loading;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpActivity.this,"Registration...","Wait...",false,false);
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SignUpActivity.this,s,Toast.LENGTH_LONG).show();
            }
 
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_DESK,desitation_android);
                params.put(Config.NAME,name_android);
                params.put(Config.KEY_ROLL,roll_android);
                params.put(Config.KEY_BRANCH,branch_android);
                params.put(Config.KEY_PASS,pass_android);
                params.put(Config.KEY_EMAIL,email_android);
               
 
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }//$$$$$$$$$$$$$$$$$$$$$$$$$$$$
 
        AddEmployee ae = new AddEmployee();
        ae.execute();
		//****************************************
			
			
			

			
		
		
	}


    
}
