package com.example.housecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InitialScreen extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
        
        Button btnCreateHouse = (Button)findViewById(R.id.btn_CreateHouse);
        Button btnEditHouse = (Button)findViewById(R.id.btn_EditHouse);
        
        btnCreateHouse.setOnClickListener(onClickListener);
        btnEditHouse.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_initial_screen, menu);
        return true;
    }
    
    
    private OnClickListener onClickListener = new OnClickListener() {  
        public void onClick(final View v) {
        	Intent intent = null;
            switch(v.getId()){
                case R.id.btn_CreateHouse:
                	intent = new Intent(InitialScreen.this, CreateHouse.class);
                    startActivity(intent);
                break;
                case R.id.btn_EditHouse:
                	
					
                	intent = new Intent(InitialScreen.this, ListHouseActivity.class);
                    startActivity(intent);
                	break;
            }
        }
    };
}
