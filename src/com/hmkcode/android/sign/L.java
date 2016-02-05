package com.hmkcode.android.sign;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {
	public static void m(String message)
	{
		Log.d("13/ics/033",message);
	}

	public static void S(Context context,String message)
	{
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
