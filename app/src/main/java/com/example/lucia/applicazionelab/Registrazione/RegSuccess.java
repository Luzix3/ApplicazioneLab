package com.example.lucia.applicazionelab.Registrazione;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucia.applicazionelab.MainETab.Loginpage;
import com.example.lucia.applicazionelab.R;

public class RegSuccess extends AppCompatActivity {

  Button btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);

        btnlog = (Button)findViewById(R.id.buttonLog);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLogPage = new Intent(RegSuccess.this, Loginpage.class);
                startActivity(openLogPage);
            }
        });
    }
}
