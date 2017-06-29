package com.example.lucia.applicazionelab.MainETab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    TabHost TabHostWindows;
    private final static String TAG =  "MainActivity";
    private final static String EXTRA_PERIODO = "periodo prenotazione";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView btnLogOut= (TextView)findViewById(R.id.textLogOut);
        TextView btnVaiLogin = (TextView)findViewById(R.id.textEffettuaLogin);
       // FirebaseAuth.getInstance().signOut();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // ho detto mi dici chi è l'utente collegato in questo
        //momento con questo account

        if (user == null)
        {
            btnVaiLogin.setVisibility(View.VISIBLE);
            //devo fare il login
            btnVaiLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openPageLogin1 = new Intent(MainActivity.this, Loginpage.class);
                    startActivity(openPageLogin1);

                }
            });

        }else
        {
            Log.d(TAG, "loggato come user" + user.getEmail());
            btnLogOut.setVisibility(View.VISIBLE);
            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent openPagelogout = new Intent (MainActivity.this, Loginpage.class);
                    startActivity(openPagelogout);
                }
            });
        }

        //inizializzo la tab kk
        TabHostWindows = (TabHost) findViewById(android.R.id.tabhost);

        //creo le tab
        TabHost.TabSpec Libri = TabHostWindows.newTabSpec("I miei libri");
        TabHost.TabSpec Cerca = TabHostWindows.newTabSpec("Cerca");
        TabHost.TabSpec Dona = TabHostWindows.newTabSpec("Dona");
        //setto le tab
        Libri.setIndicator("I miei libri");
        //setto le activity di arrivo

        Libri.setContent(new Intent (this, LibriActivity.class));

        //faccio lo stesso per le altre tab

        Cerca.setIndicator("Cerca");
        Cerca.setContent(new Intent (this,CercaActivity.class));

        Dona.setIndicator("Dona");
        Dona.setContent(new Intent (this,DonaActivity.class));

        //aggiungo alla tabhost

        TabHostWindows.addTab(Libri);
        TabHostWindows.addTab(Cerca);
        TabHostWindows.addTab(Dona);
    }
}