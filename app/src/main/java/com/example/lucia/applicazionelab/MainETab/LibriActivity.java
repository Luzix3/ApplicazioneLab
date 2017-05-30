package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.lucia.applicazionelab.Database.Utente;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.LibroAdapter;

import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LibriActivity extends AppCompatActivity {


    private ListView listaMieiLibri;

    DataStore archivio2;

    // Autenticazione Firebase
    private FirebaseAuth mAuth6;


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
        Utente u = new Utente();
        /*
        u.setEmail(user5.getEmail());
        String user= user5.getUid();
        DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("Miei Libri");
        mDatabase1.child(user).setValue(user5.getEmail());

*/








        // mDatabase1.child(userId).setValue(user5.getUid());















    }








}
