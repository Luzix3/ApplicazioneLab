package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucia.applicazionelab.Database.DataStore;
import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.LibroAdapter;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class DettagliMioLibro extends AppCompatActivity {

    //widget
    private TextView mNome;
    private TextView mPrenotato;
    private TextView mConsegna;
    private ImageView mCestino;
    DataStore archivioprenotazioni = new DataStore();
    private FirebaseAuth mAuth;

    private final static String EXTRA_LIBRO2 = "libro2";
    private final static String EXTRA_PRENOTAZIONI = "prenotazioni";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_mio_libro);
        mNome = (TextView)findViewById(R.id.textNome2);
        mPrenotato = (TextView)findViewById(R.id.textPrenotato);
        mConsegna= (TextView)findViewById(R.id.textConsegna);
        mCestino = (ImageView)findViewById(R.id.imageCestino);

        Intent i = getIntent();
        final Libro libro = (Libro) i.getSerializableExtra(EXTRA_LIBRO2);

        if (libro != null) {
            mNome.setText(libro.getNome());
            mPrenotato.setText(Integer.toString(libro.getGiorni()) + " giorni.");
            mConsegna.setText(libro.getDataconsegna());
        }


        mCestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();
                // Comportamento differenziato
                FirebaseUser user = mAuth.getCurrentUser();
                String user2 = user.getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Utenti").child(user2).child(EXTRA_PRENOTAZIONI);
                archivioprenotazioni.eliminaPrenotazione(libro.getCodlibro());

                Intent i = new Intent(DettagliMioLibro.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
