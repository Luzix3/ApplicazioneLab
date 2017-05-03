package com.example.lucia.applicazionelab;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button)findViewById(R.id.buttonLogin);

        //si apre solo se utente e password sono settate e se user e password corrispondono a qualcosa
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // definisco l'intenzione di aprire l'Activity "Page1.java"
                Intent openPage1 = new Intent(MainActivity.this, Page1.class);
                // passo all'attivazione dell'activity page1.java
                startActivity(openPage1);
            }

        });

    }



}
