package com.example.lucia.applicazionelab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucia.applicazionelab.Login.Page1;
import com.example.lucia.applicazionelab.Passworddim.PagePassDim;
import com.example.lucia.applicazionelab.Registrazione.Page2reg;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button)findViewById(R.id.buttonLogin);

        /**
         * todo: mettere i dati con il database
         * todo: devi settare il fatto che non può clccarsi il bottone da solo: aggiungi degli if
         *
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // definisco l'intenzione di aprire l'Activity "Page1.java"
                Intent openPage1 = new Intent(MainActivity.this, Page1.class);
                // passo all'attivazione dell'activity page1.java
                startActivity(openPage1);
            }

        });


        Button btnRegistrati = (Button)findViewById(R.id.buttonRegistrati);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPage2reg = new Intent(MainActivity.this, Page2reg.class);
                startActivity(openPage2reg);
            }
        });

        TextView btnPassword = (TextView)findViewById(R.id.textPassdimenticata);

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagePassDim = new Intent(MainActivity.this, PagePassDim.class);
                startActivity(openPagePassDim);

            }
        });


    }



}
