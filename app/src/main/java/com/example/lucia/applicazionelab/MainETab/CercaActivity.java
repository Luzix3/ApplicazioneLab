package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.LibroAdapter;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CercaActivity extends AppCompatActivity {


    // Costanti
    private final static String EXTRA_LIBRO = "libro";
    private final static String TAG = "BiblApp";

    // Widget
    private ListView listaLibri;

    private DataStore archivio = new DataStore();

    // Adapter
    private LibroAdapter adapter;

      // Autenticazione Firebase
    private FirebaseAuth mAuth5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cerca);

        // Autenticazione Firebase
        mAuth5 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user4 = mAuth5.getCurrentUser();
        if (user4 == null) {
            // Utente non autenticato, voglio impedire l'accesso
            //devo impedirlo o meno?
            //todo: vedi bene questa riga
            /*
            Intent intent = new Intent(this, Loginpage.class);
            startActivity(intent);
            */
        } else {
            // Utente autenticato.
            // Nessuna operazione richiesta
        }

        listaLibri = (ListView)findViewById(R.id.ListaLibri);
        adapter = new LibroAdapter(this);

        archivio.iniziaOsservazioneLibri(new DataStore.UpdateListener() {
            @Override
            public void libriAggiornati() {


               adapter.update(archivio.elencoLibri());
            }
        });

        listaLibri.setAdapter(adapter);
        listaLibri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), SpecLibro.class);
                intent.putExtra(EXTRA_LIBRO, libro);
                startActivity(intent);
            }
        });

        Libro l = new Libro();
        l.setCodlibro("0000001");
        l.setNome("Harry Potter 1");
        l.setAutore("j.k. rowling");
        l.setGenere("Fantasy");
        l.setAnno("2002");
        archivio.aggiungiLibro(l);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneLibri();
    }








}

