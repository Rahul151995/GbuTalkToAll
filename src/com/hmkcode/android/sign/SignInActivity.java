package com.hmkcode.android.sign;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SignInActivity extends Activity {

	String urlString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        
        //getting button and edittext ready
    EditText enrollement_no = (EditText) findViewById(R.id.RegistrationNo);
    EditText pass_word = (EditText) findViewById(R.id.Password);
    Button sign_in = (Button) findViewById(R.id.btnSingIn); 
    sign_in.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          
            urlString = "http://gbutalktoall.esy.es/reg_data_enc.php";
            new ProcessJSON().execute(urlString);
        }
    });
}

private class ProcessJSON extends AsyncTask<String, Void, String>{
    protected String doInBackground(String... strings){
        String stream = null;
        String urlString = strings[0];

        HTTPDataHandler hh = new HTTPDataHandler();
        stream = hh.GetHTTPData(urlString);

        // Return the data from specified url
        return stream;
    }

    protected void onPostExecute(String stream){
          /*
            Important in JSON DATA
            -------------------------
            * Square bracket ([) represents a JSON array
            * Curly bracket ({) represents a JSON object
            * JSON object contains key/value pairs
            * Each key is a String and value may be different data types
         */

        //..........Process JSON DATA................
    	  EditText enrollement_no = (EditText) findViewById(R.id.RegistrationNo);
    	    EditText pass_word = (EditText) findViewById(R.id.Password);
    	    
    	    String enrollement_no_rec = enrollement_no.getText().toString();
    	    String pass_word_rec =  pass_word.getText().toString();
    	    
        if(stream !=null){
                   	        	
        	try{
                
        		
        		 //tv.setText(stream);
        		 JSONArray jsonArray = new JSONArray(stream);
        		 for (int i=0; i < jsonArray.length(); i++)
        		 {
        		     try {
        		         JSONObject eachObject = jsonArray.getJSONObject(i);
        		         // Getting items from the json array
        		         
        		         String registration_id= eachObject.getString("5"); //now checking email
        		         String registration_pass= eachObject.getString("4");
        		         
        		         if((registration_id).equals(enrollement_no_rec) && (registration_pass).equals(pass_word_rec))
        		         {    
        		        	 String frome_name= eachObject.getString("1");
        		        	 
        		        	 SharedPreferences q = getSharedPreferences("mydata", Context.MODE_WORLD_READABLE);
        		        	 SharedPreferences.Editor edit;
        		        	 edit = q.edit();
        		        	 edit.putString("From", frome_name);
        		        	 edit.commit();
        		        	 
        		        	 
        		        	 
        		        	 
        		        	 Intent m = new Intent(SignInActivity .this,MainActivitys.class);
        		        	 startActivity(m);
        		        	 L.S(SignInActivity.this, "Sign in Successful");
        		        	 
        		        	 //Go to another activity
        		        	 break;
        		         }
        		         else
        		         {
        		        	 L.S(SignInActivity.this, "Sign in failed");
        		         }
        		        
        		     } catch (JSONException e) {
        		         // handle exception
        		     }
        		 }
        		
        		
        	
            
                                 

            }catch(JSONException e){
                e.printStackTrace();
            }

        } // if statement end
    } // onPostExecute() end
} // ProcessJSON class end
} // Activity end