package com.example.housecontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
	
	private final String kGetEquipmentStateRelativeUrl = "/AndroidCommService/EquipmentState";
	private final String kChangeEquipmentStateRelativeUrl = "/AndroidCommService/ChangeEquipmentState";
	
	private final String kEquipmentStateJSONKey = "EquipmentStateResult";
	private final String kChangeEquipmentStateJSONKey = "ChangeEquipmentStateResult";

	public ConfigureEquipmentsListAdapter(Context context, int resource, List<Equipment> objects) {
		super(context, resource, objects);
		
		this.mContext = context;
		this.mLayoutResourceId = resource;
		this.mEquipmentsArrayList = (ArrayList<Equipment>) objects;
	}
	
	public void setEquipments(final ArrayList<Equipment> aArrayList)
	{
		this.mEquipmentsArrayList = aArrayList;
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
			holder.mToogleButton = (ToggleButton)row.findViewById(R.id.toggleButton);
			holder.mTxtEquipmentTitle = (TextView)row.findViewById(R.id.txtEquipmentName); 
			row.setTag(holder);
		} else {
			holder = (EquipmentHolder)row.getTag();
		}
	        
		final Equipment equipment = this.mEquipmentsArrayList.get(position);
		holder.mEquipmentIp = equipment.getIp();
		
		new RequestStateAsyncTask().execute(holder);
		 
		final EquipmentHolder param = holder;
		
		holder.mTxtEquipmentTitle.setText(getEquipmentNameFromType(equipment.getEquipmentType()));
		holder.mToogleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (equipment.getIp() != null && equipment.getIp().length() > 1) {
					new RequestStateChangeAsyncTask().execute(param);
				} else {
					Toast.makeText(mContext, R.string.please_config_equipment, Toast.LENGTH_SHORT).show();
					param.mToogleButton.setChecked(!param.mToogleButton.isChecked());
				}
			}
		});
		
		return row;
	}
	
	public int getCount() {
		return this.mEquipmentsArrayList.size();
	}

	public Equipment getItem(int arg0) {
		return this.mEquipmentsArrayList.get(arg0);
	}

	public long getItemId(int arg0) {
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
        private ToggleButton mToogleButton;
        private TextView mTxtEquipmentTitle;
        private String mEquipmentIp;
    }
	
	
	private class RequestStateAsyncTask extends AsyncTask<EquipmentHolder, Integer, Boolean>
	{
		private ToggleButton mButtonToUpdate;
		
		@Override
		protected Boolean doInBackground(EquipmentHolder... params) {
			String urlString = "http://"+ApplicationGlobals.SERVER_IP+kGetEquipmentStateRelativeUrl;
			EquipmentHolder eqHolder = params[0];
			this.mButtonToUpdate = eqHolder.mToogleButton;
			URL url;
			try {
				url = new URL(urlString);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				return null;
			}
			
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
				e.printStackTrace();
				return null;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			
			Boolean equipmentStateResult = false;
			try {
				equipmentStateResult = jsonResponse.getBoolean(kEquipmentStateJSONKey);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			
			return equipmentStateResult;
		}
		
		@Override
		protected void onPostExecute(Boolean aResult) {
			if (aResult != null && aResult == true) {
				this.mButtonToUpdate.setChecked(true);
			} else {
				this.mButtonToUpdate.setChecked(false);
			}
			return;
		}
	}
	
	private class RequestStateChangeAsyncTask extends AsyncTask<EquipmentHolder, Integer, Boolean>
	{		
		@Override
		protected Boolean doInBackground(EquipmentHolder... params) {
			EquipmentHolder eqHolder = params[0];
			String urlString = "http://"+ApplicationGlobals.SERVER_IP+kChangeEquipmentStateRelativeUrl;
			String query = null;
			try {
				query = String.format("?IP=%s", URLEncoder.encode(eqHolder.mEquipmentIp, "UTF-8"));
			} catch (UnsupportedEncodingException e2) {
				return null;
			}
			
			URL url;
			try {
				url = new URL(urlString+query);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				return null;
			}
			
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
				e.printStackTrace();
				return null;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			} 
			
			Boolean equipmentStateResult = false;
			try {
				equipmentStateResult = jsonResponse.getBoolean(kChangeEquipmentStateJSONKey);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			return equipmentStateResult;
	}
	
		@Override
		protected void onPostExecute(Boolean aResult) {
			if (aResult != null && aResult == true) {
				Toast.makeText(mContext, R.string.equipment_changed_successfully, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, R.string.equipment_changed_failed, Toast.LENGTH_SHORT).show();
			}
			return;
		}
	}
}
