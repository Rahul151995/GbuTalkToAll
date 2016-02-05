package com.hmkcode.android.sign;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.ListView;

public class MainActivitys extends ListActivity {
	 private ProgressDialog pDialog;
	 Button refresh;
	 Button post;
	    // URL to get contacts JSON
	    private static String url = "http://gbutalktoall.esy.es/json_ecnod.php";
	 
	    private static String urlStrings  = "http://gbutalktoall.esy.es/json_ecnod.php";
	 
	    // the real data to received
	    private static final String TAG_Id = "coid";
	    private static final String TAG_SenderName = "from";
	    private static final String Content = "content";
	    private static final String TAG_Date = "date";
	    private static final String TAG_Subject = "subj";
	    
	    
	    
	    
	    // contacts JSONArray
	    JSONArray contacts = null;
	 
	    // Hashmap for ListView
	    ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mains);
		contactList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();


		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name))  
						.getText().toString();
				
				String cost = ((TextView) view.findViewById(R.id.email))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.mobile))
						.getText().toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						SingleContactActivity.class);
			
		
				
				
				in.putExtra(Content, name); //content
				in.putExtra(TAG_Date, cost); //from
				in.putExtra(TAG_Subject , description); //
				startActivity(in);

			}
		});
		
		post = (Button) findViewById(R.id.Post);
		
		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent k = new Intent(MainActivitys.this,Post_Message.class);
	        	 startActivity(k);
				
			}
		} );
		
		
 refresh = (Button) findViewById(R.id.Refresh);
 
 refresh.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new GetContacts().execute();
		
	}
} );
		// Calling async task to get json
 new GetContacts().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivitys.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

	
		protected Void doInBackground(Void...params) {
					
			 String stream = null;
		       // String urlStrings = strings[0];

		        HTTPDataHandlers hh = new HTTPDataHandlers();
		        stream = hh.GetHTTPData(urlStrings);

		        // Return the data from specified url
		    
			if (stream != null) {
				try {
					JSONArray jsonArray = new JSONArray(stream);
					 for (int i=0; i < jsonArray.length(); i++)
	        		 {
	        		     try {
	        		         JSONObject eachObject = jsonArray.getJSONObject(i);
	        		         // Getting items from the json array
	        		         		        
	        		     // 	String coid = eachObject.getString(TAG_Id);
							String from = eachObject.getString(TAG_SenderName);
							String content = eachObject.getString(Content);
							String date = eachObject.getString(TAG_Date);
							String subj = eachObject.getString(TAG_Subject);
							//L.S(MainActivitys.this, coid + from + content + date + subj);
							//Log.d("Response: ", "> " + coid + from + content + date + subj);
							
					
							HashMap<String, String> contact = new HashMap<String, String>();
                             //my data
							//contact.put(TAG_Id, coid);
						    contact.put(TAG_SenderName, from);
							contact.put(Content , content);
							contact.put(TAG_Date, date);
							contact.put(TAG_Subject, subj);
							contactList.add(contact);      
						
	        		     } catch (JSONException e) {
	        		         // handle exception
	        		     }
	        		 }
	        	
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}
		
		
	
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
		ListAdapter adapter = new SimpleAdapter(
					MainActivitys.this, contactList,
					R.layout.list_item, new String[] { TAG_SenderName, Content,
							TAG_Subject }, new int[] { R.id.name,
							R.id.email, R.id.mobile });

			setListAdapter(adapter);
			
		}

	

	}

}