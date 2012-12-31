package com.example.housecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginScr extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scr);
        
        Button btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login_scr, menu);
        return true;
    }
    
    private OnClickListener onClickListener = new OnClickListener() {  
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btn_login:
                	Intent intent = new Intent(LoginScr.this, InitialScreen.class);
                    startActivity(intent);
                break;
            }
        }
    };
}
