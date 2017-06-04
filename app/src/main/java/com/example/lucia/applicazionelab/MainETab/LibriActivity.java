package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lucia.applicazionelab.Database.DataStore;
import com.example.lucia.applicazionelab.Database.DataStore2;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.LibroAdapter;

import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LibriActivity extends AppCompatActivity {


    private ListView listaMieiLibri;

    private DataStore2 archivio2 = new DataStore2();
    private final static String EXTRA_PERIODO = "periodo prenotazione";

    // Adapter
    private LibroAdapter adapter1;
    private final static String EXTRA_LIBRO2 = "libro2";

    // Autenticazione Firebase
    private FirebaseAuth mAuth6;

    ArrayList<Libro> arraylist = new ArrayList<Libro>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_libri);


        // Autenticazione Firebase
        mAuth6 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user5 = mAuth6.getCurrentUser();


        if (user5 == null) {

            Intent intent = new Intent(this, Loginpage.class);
            startActivity(intent);

        } else {
            // Utente autenticato.
            // Nessuna operazione richiesta
        }

        listaMieiLibri = (ListView)findViewById(R.id.ListaMieiLibri);
        adapter1 = new LibroAdapter(this,arraylist);

        archivio2.iniziaOsservazioneLibri2(new DataStore.UpdateListener() {
            @Override
            public void libriAggiornati() {

                adapter1.update(archivio2.elencoLibri2());
            }
        });

        listaMieiLibri.setAdapter(adapter1);





        listaMieiLibri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = adapter1.getItem(position);
                Intent intent = new Intent(view.getContext(), DettagliMioLibro.class);
                 intent.putExtra(EXTRA_LIBRO2, libro);
                 startActivity(intent);
            }
        });




        /*
        u.setEmail(user5.getEmail());
        String user= user5.getUid();
        DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("Miei Libri");
        mDatabase1.child(user).setValue(user5.getEmail());

*/








        // mDatabase1.child(userId).setValue(user5.getUid());















    }








}
