package com.example.lucia.applicazionelab.MainETab;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.lucia.applicazionelab.MainETab.CercaActivity;
import com.example.lucia.applicazionelab.MainETab.DonaActivity;
import com.example.lucia.applicazionelab.MainETab.LibriActivity;
import com.example.lucia.applicazionelab.R;



@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    TabHost TabHostWindows;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //inizializzo la tab kk
        TabHostWindows = (TabHost) findViewById(android.R.id.tabhost);

        //creo le tab
        TabHost.TabSpec Libri = TabHostWindows.newTabSpec("Libri");
        TabHost.TabSpec Cerca = TabHostWindows.newTabSpec("Cerca");
        TabHost.TabSpec Dona = TabHostWindows.newTabSpec("Dona");
        //setto le tab
        Libri.setIndicator("Libri");
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









/*
        Resources res = getResources();
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // attività per la tabhost
        TabHost.TabSpec spec; // tabspec riutilizzabile da ogni tab
        Intent intent; //intent riutilizzabile per ogni tab

        // crea un intent per lanciare un'attività per la tab
        intent = new Intent().setClass(this, Libri.class);

        // inizializza una tabspec per ogni tab e lo aggiunge al tabhost
        spec = tabHost.newTabSpec("Libri").setIndicator("Libri").setContent(intent);
        tabHost.addTab(spec);


        intent = new Intent().setClass(this, Cerca.class);
        spec = tabHost.newTabSpec("Cerca").setIndicator("Cerca").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Dona.class);
        spec = tabHost.newTabSpec("Dona").setIndicator("Dona").setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTabByTag("Libri");

*/


    }
}