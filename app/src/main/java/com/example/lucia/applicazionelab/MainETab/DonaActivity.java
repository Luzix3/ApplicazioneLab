package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucia.applicazionelab.R;

public class DonaActivity extends AppCompatActivity {


    Button btnDona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dona);

        btnDona = (Button)findViewById(R.id.buttonProcediLibro);

        btnDona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagedonaend = new Intent(DonaActivity.this, Dona2.class);
                startActivity(openPagedonaend);
            }
        });



    }
}
