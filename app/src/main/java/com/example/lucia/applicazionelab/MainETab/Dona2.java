package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucia.applicazionelab.R;

public class Dona2 extends AppCompatActivity {


    Button btngoback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dona2);


        btngoback = (Button)findViewById(R.id.buttonTornaLog);

        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagemain = new Intent(Dona2.this, MainActivity.class);
                startActivity(openPagemain);
            }
        });

    }
}
