package com.josesp.gametictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DificultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        fullScreen();
        setContentView(R.layout.activity_dificulty);
    }

    public void fullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void btnEasy_onclick(View view){
        goToGamePlay(1);
    }

    public void btnNormal_onclick(View view){
        goToGamePlay(2);
    }

    public void btnHard_onclick(View view){
        goToGamePlay(3);
    }

    public void goToGamePlay(int difficulty){
        Intent intent = new Intent(this, OnePlayerGameActivity.class);
        intent.putExtra("difficulty",difficulty);
        startActivity(intent);
    }

}
