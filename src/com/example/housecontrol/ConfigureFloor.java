package com.example.housecontrol;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ConfigureFloor extends TabActivity {

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_floor);
        
       /* Intent myIntent= getIntent(); // gets the previously created intent
		Floor clsFloor = (Floor)myIntent.getSerializableExtra(FloorCreator.mSFirstFloorKey);
		
		System.out.println("Nm Andar - " + clsFloor.getFloorNb());
		System.out.println("Numero de Quartos - " + clsFloor.getBedroomNb());
		System.out.println("Numero de Salas - " + clsFloor.getLivingroomNb());
		System.out.println("Numero de Cozinhas - " + clsFloor.getKitchenNb());
		System.out.println("Numero de WC - " + clsFloor.getWCNb());
		
		clsFloor = (Floor)myIntent.getSerializableExtra(FloorCreator.mSSecondFloorKey);
		
		System.out.println("Nm Andar - " + clsFloor.getFloorNb());
		System.out.println("Numero de Quartos - " + clsFloor.getBedroomNb());
		System.out.println("Numero de Salas - " + clsFloor.getLivingroomNb());
		System.out.println("Numero de Cozinhas - " + clsFloor.getKitchenNb());
		System.out.println("Numero de WC - " + clsFloor.getWCNb());*/
		
        TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabSpec spec1=tabHost.newTabSpec("Tab 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Tab 1");
        TabSpec spec2=tabHost.newTabSpec("Tab 2");
        spec2.setIndicator("Tab 2");
        spec2.setContent(R.id.tab2);
        TabSpec spec3=tabHost.newTabSpec("Tab 3");
        spec3.setIndicator("Tab 3");
        spec3.setContent(R.id.tab3);
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);  
        tabHost.addTab(spec3);	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_configure_floor, menu);
        return true;
    }
}
