package com.example.housecontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class FloorCreator extends Activity {

	public static final String mSNrFloorKey = "numberOfFloorsKey";
	public static final String mSFirstFloorKey = "keyFirstFloor";
	public static final String mSSecondFloorKey = "keySecondFloor";
	public static final String mSThirdFloorKey = "keyThirdFloor";
	public static final String mSFloorsKey = "keyFloors";
	
	private RelativeLayout m_lSecondFloor;
	private RelativeLayout m_lThirdFloor;
	private Button mButtonNext;
	private int m_iNrFloors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_creator);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		
		m_lSecondFloor = (RelativeLayout)findViewById(R.id.relativeLayoutSecondFloor);
		m_lThirdFloor = (RelativeLayout)findViewById(R.id.relativeLayoutThirdFloor);
		
		m_lSecondFloor.setVisibility(View.GONE);
		m_lThirdFloor.setVisibility(View.GONE);
		
		Intent myIntent= getIntent(); // gets the previously created intent
		m_iNrFloors = myIntent.getIntExtra(CreateHouse.kNrFloors, 1);
		
		if(m_iNrFloors >= 2)
		{
			m_lSecondFloor.setVisibility(View.VISIBLE);
			
			if(m_iNrFloors==3)
				m_lThirdFloor.setVisibility(View.VISIBLE);
		}
		
		mButtonNext = (Button)findViewById(R.id.btn_NextFloorForm);
        mButtonNext.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				Bundle params = new Bundle();
				params.putInt(mSNrFloorKey, m_iNrFloors);

				//primeiro andar
				EditText sPNrSala1 = (EditText) findViewById(R.id.txbPrNrSala);
				EditText sPNrQuarto1 = (EditText) findViewById(R.id.txbPrNrQuarto);
				EditText sPNrWC1 = (EditText) findViewById(R.id.txbPrNrWC);
				EditText sPNrCozinha1 = (EditText) findViewById(R.id.txbPrNrCozinha);
				
				Floor clsFloor1 = new Floor();
				clsFloor1.setFloorNb(1);
				clsFloor1.setLivingroomNb(Integer.parseInt(sPNrSala1.getText().toString()));
				clsFloor1.setKitchenNb(Integer.parseInt(sPNrCozinha1.getText().toString()));
				clsFloor1.setWCNb(Integer.parseInt(sPNrWC1.getText().toString()));
				clsFloor1.setBedroomNb(Integer.parseInt(sPNrQuarto1.getText().toString()));
				params.putSerializable(mSFirstFloorKey, clsFloor1);
						
				if(m_iNrFloors >= 2)
				{
					//segundo Andar
					EditText sSNrSala2 = (EditText) findViewById(R.id.txbSgNrSala);
					EditText sSNrQuarto2 = (EditText) findViewById(R.id.txbSgNrQuarto);
					EditText sSNrWC2 = (EditText) findViewById(R.id.txbSgNrWC);
					EditText sSNrCozinha2 = (EditText) findViewById(R.id.txbSgNrCozinha);
				
					Floor clsFloor2 = new Floor();
					clsFloor2.setFloorNb(2);
					clsFloor2.setLivingroomNb(Integer.parseInt(sSNrSala2.getText().toString()));
					clsFloor2.setKitchenNb(Integer.parseInt(sSNrCozinha2.getText().toString()));
					clsFloor2.setWCNb(Integer.parseInt(sSNrWC2.getText().toString()));
					clsFloor2.setBedroomNb(Integer.parseInt(sSNrQuarto2.getText().toString()));
					params.putSerializable(mSSecondFloorKey, clsFloor2);
					if(m_iNrFloors > 2)
					{
						// terceiro andar
						EditText sTNrSala3 = (EditText) findViewById(R.id.txbTcNrSala);
						EditText sTNrQuarto3 = (EditText) findViewById(R.id.txbTcNrQuarto);
						EditText sTNrWC3 = (EditText) findViewById(R.id.txbTcNrWC);
						EditText sTNrCozinha3 = (EditText) findViewById(R.id.txbTcNrCozinha);
						
						Floor clsFloor3 = new Floor();
						clsFloor3.setFloorNb(3);
						clsFloor3.setLivingroomNb(Integer.parseInt(sTNrSala3.getText().toString()));
						clsFloor3.setKitchenNb(Integer.parseInt(sTNrCozinha3.getText().toString()));
						clsFloor3.setWCNb(Integer.parseInt(sTNrWC3.getText().toString()));
						clsFloor3.setBedroomNb(Integer.parseInt(sTNrQuarto3.getText().toString()));
						params.putSerializable(mSThirdFloorKey, clsFloor2);		
					}
				}	
				
				Intent floorCreator = new Intent(FloorCreator.this, ConfigureFloorActivity.class);
				floorCreator.putExtras(params);
				startActivity(floorCreator);
			}
        });
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_floor_creator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
