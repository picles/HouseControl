package com.example.housecontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ConfigureEquipmentsListAdapter extends ArrayAdapter<Equipment> {
	
	private Context mContext = null;
	private int mLayoutResourceId;
	private ArrayList<Equipment> mEquipmentsArrayList;
	
	private String mIp = "http://192.168.1.65:9090/AndroidCommService/EquipmentState";

	public ConfigureEquipmentsListAdapter(Context context, int resource, List<Equipment> objects) {
		super(context, resource, objects);
		
		this.mContext = context;
		this.mLayoutResourceId = resource;
		this.mEquipmentsArrayList = (ArrayList<Equipment>) objects;
		System.out.println("-> "+this.mEquipmentsArrayList);
	}
	
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) 
	{
		View row = convertView;
		EquipmentHolder holder = null;
		
		 if(row == null) {
			 LayoutInflater inflater = ((Activity) this.mContext).getLayoutInflater();
			 row = inflater.inflate(mLayoutResourceId, parent, false);
		            
			 holder = new EquipmentHolder();
			 holder.toogleButton = (ToggleButton)row.findViewById(R.id.toggleButton);
			 holder.txtEquipmentTitle = (TextView)row.findViewById(R.id.txtEquipmentName); 
			 row.setTag(holder);
		 } else {
			 holder = (EquipmentHolder)row.getTag();
		 }
	        
		 final Equipment equipment = this.mEquipmentsArrayList.get(position);

		 URL url = null;
		 try {
			url = new URL(mIp);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 new RequestStateAsyncTask().execute(url);
		 
		 holder.txtEquipmentTitle.setText(getEquipmentNameFromType(equipment.getEquipmentType()));
		 holder.toogleButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				ToggleButton b = (ToggleButton)view;
				Toast.makeText(getContext(), "Whoop!"+equipment.getEquipmentId()+" -- "+b.isChecked(), Toast.LENGTH_SHORT).show();
				
			}
		});
	        
		 return row;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mEquipmentsArrayList.size();
	}

	public Equipment getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.mEquipmentsArrayList.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getEquipmentNameFromType(final int aEquipmentType)
	{
		switch (aEquipmentType) {
		case ApplicationGlobals.kEstoresDBKey:
			return this.mContext.getString(R.string.blinds_name);
		case ApplicationGlobals.kLuzesDBKey:
			return this.mContext.getString(R.string.lights_name);
		case ApplicationGlobals.kPortasDBKey:
			return this.mContext.getString(R.string.doors_name);
		default:
			return "";
		}
	}

	static class EquipmentHolder
    {
        ToggleButton toogleButton;
        TextView txtEquipmentTitle;
    }
	
	
	private class RequestStateAsyncTask extends AsyncTask<URL, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(URL... params) {
			URL url = params[0];
			URLConnection jc;
			BufferedReader reader;
			JSONObject jsonResponse = null;
			try {
				jc = url.openConnection();
				reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
				String line = reader.readLine();
				jsonResponse = new JSONObject(line);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			System.out.println("Async: "+jsonResponse);
			return 0;
		}
		
		@Override
		protected void onPostExecute(Integer aResult) {
			
		}
		
	}
	
	private class RequestStateChangeAsyncTask extends AsyncTask<URL, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(URL... urls) {
			URL url = urls[0];
			return 0;
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         //setProgressPercent(progress[0]);
	     }

	     protected void onPostExecute(Long result) {
	         //showDialog("Downloaded " + result + " bytes");
	     }
		
	}
	
	
	
	
	

}
