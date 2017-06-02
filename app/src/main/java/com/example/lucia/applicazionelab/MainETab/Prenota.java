package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.Utente;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Prenota extends AppCompatActivity {
    private final static String EXTRA_LIBRO2 = "libro2";
    private final static String EXTRA_NOME = "nome libro";
    private final static String EXTRA_AUTORE = "autore";
    private final static String EXTRA_CODICE = "codice";
    private final static String EXTRA_GENERE = "genere";
    private final static String EXTRA_ANNO = "anno";



Button prenota;
    // Autenticazione Firebase
    private FirebaseAuth mAuth6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota);
        // Autenticazione Firebase

        Intent intent1 = getIntent();
        final Libro libro2 = (Libro)intent1.getSerializableExtra(EXTRA_LIBRO2);


        mAuth6 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        final FirebaseUser user6 = mAuth6.getCurrentUser();





        prenota = (Button)findViewById(R.id.ButtonPrenota);
        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Miei Libri");
                Libro libro1 = new Libro(libro2.getAutore(), libro2.getCodlibro(), libro2.getNome(), libro2.getAnno(), libro2.getGenere());

                ref.child(user6.getUid()).push().setValue(libro1);

                Intent intent1 = new Intent(Prenota.this, LibriActivity.class);

                intent1.putExtra(EXTRA_CODICE, libro1.getCodlibro());
                intent1.putExtra(EXTRA_NOME, libro1.getNome());
                intent1.putExtra(EXTRA_ANNO, libro1.getAnno());
                intent1.putExtra(EXTRA_AUTORE, libro1.getAutore());
                intent1.putExtra(EXTRA_GENERE, libro1.getGenere());





            }
        });


    }
}
