package com.josesp.gametictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_menu_principal);
    }

    public void fullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onePlayer_click(View view){
        Intent intent = new Intent(this, DificultyActivity.class);
        startActivity(intent);
    }

    public void points_click(View view){
        Intent intent = new Intent(this, TablePointsActivity.class);
        startActivity(intent);
    }

    public void twoPlayer_click(View view){
        Toast.makeText(this,"Proximamente",Toast.LENGTH_LONG).show();
    }

    public void exit(View view){
        finishAffinity();
    }

}


