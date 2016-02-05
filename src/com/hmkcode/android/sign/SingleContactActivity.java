package com.hmkcode.android.sign;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SingleContactActivity extends Activity {
	// JSON node keys
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_PHONE_MOBILE = "mobile";
	
	
	
    // the real data to received
    private static final String TAG_Id = "coid";
    private static final String TAG_SenderName = "from";
    private static final String Content = "content";
    private static final String TAG_Date = "date";
    private static final String TAG_Subject = "subj";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		// getting intent data
		Intent in = getIntent();

		// Get JSON values from previous intent
		String name = in.getStringExtra("my tag");
		String email = in.getStringExtra(TAG_EMAIL);
		String mobile = in.getStringExtra(TAG_PHONE_MOBILE);
		
		
		
		//my data @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		// my data
		String coid = in.getStringExtra(TAG_Id);
		String from = in.getStringExtra(TAG_SenderName);
		String content = in.getStringExtra(Content);
		String date = in.getStringExtra(TAG_Date);
		String subj = in.getStringExtra(TAG_Subject);
		
		


		
		

		// Displaying all values on the screen
		TextView lblName = (TextView) findViewById(R.id.name);
		TextView lblEmail = (TextView) findViewById(R.id.email);
		TextView lblMobile = (TextView) findViewById(R.id.mobile);

		lblName.setText(content);
		lblEmail.setText(email);
		lblMobile.setText(mobile);
		
		
		//my data &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		
		lblName.setText(name);
		lblEmail.setText(subj);
		lblMobile.setText(date);

		}

		}