package com.example.lucia.applicazionelab.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucia.applicazionelab.MainETab.MainActivity;
import com.example.lucia.applicazionelab.Passworddim.PagePassDim;
import com.example.lucia.applicazionelab.R;
import com.example.lucia.applicazionelab.Registrazione.Page2reg;

public class Loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        Button btnLogin = (Button)findViewById(R.id.buttonLogin);

        /**
         * todo: mettere i dati con il database
         * todo: devi settare il fatto che non può clccarsi il bottone da solo: aggiungi degli if
         *
         */

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                // definisco l'intenzione di aprire l'Activity "MainActivity.java"
                Intent openMainActivity = new Intent(Loginpage.this, MainActivity.class);
                // passo all'attivazione dell'activity MainActivity.java
                startActivity(openMainActivity);
            }

        });


        Button btnRegistrati = (Button)findViewById(R.id.buttonRegistrati);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPage2reg = new Intent(Loginpage.this, Page2reg.class);
                startActivity(openPage2reg);
            }
        });

        TextView btnPassword = (TextView)findViewById(R.id.textPassdimenticata);

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagePassDim = new Intent(Loginpage.this, PagePassDim.class);
                startActivity(openPagePassDim);

            }
        });


    }



}
