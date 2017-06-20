package com.example.lucia.applicazionelab.MainETab;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("deprecation")

public class SpecLibro extends ActionBarActivity {

    // Costanti
    private final static String EXTRA_LIBRO = "libro";
    private final static String EXTRA_NOME = "nome libro";
    private final static String EXTRA_AUTORE = "autore";
    private final static String EXTRA_CODICE = "codice";
    private final static String EXTRA_GENERE = "genere";
    private final static String EXTRA_ANNO = "anno";
    private final static String EXTRA_LIBRO2 = "libro2";

    // Widget
    private TextView mCodLibro;
    private TextView mLibro;
    private TextView mAutore;
    private TextView mGenere;
    private TextView mAnno;
    private ImageView mImageLibro;
    TextView prenota;
    private FirebaseAuth mauth;

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReferenceFromUrl("gs://biblapp-432f7.appspot.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_libro);

        // Imposto gli id widget
        mCodLibro = (TextView)findViewById(R.id.textCodLibro2);
        mAutore = (TextView)findViewById(R.id.textAutore2);
        mLibro = (TextView)findViewById(R.id.textLibro2);
        mGenere = (TextView)findViewById(R.id.textGenere2);
        mAnno= (TextView)findViewById(R.id.textAnno2);
        mImageLibro = (ImageView)findViewById(R.id.ImmagineLibro);

        final String cod1 = mCodLibro.getText().toString();

        // Ottengo i dati passati ed eventualmente visualizzo il libro
        Intent intent = getIntent();
        final Libro libro = (Libro)intent.getSerializableExtra(EXTRA_LIBRO);

        StorageReference childRef = storageRef.child(cod1);

        if (libro != null) {
            mCodLibro.setText(libro.getCodlibro());
            mAutore.setText(libro.getAutore());
            mLibro.setText(libro.getNome());
            mAnno.setText(libro.getAnno());
            mGenere.setText(libro.getGenere());
            // Load the image using Glide
            Glide.with(getApplicationContext())
                    .using(new FirebaseImageLoader())
                    .load(childRef)
                    .into(mImageLibro);

        }
        mauth = FirebaseAuth.getInstance();
        prenota= (TextView)findViewById(R.id.textPrenota);
        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user1 = mauth.getCurrentUser();
                   if (user1 == null)
                   {
                       Toast.makeText(getApplicationContext(),"Devi effettuare il login per poter prenotare!", Toast.LENGTH_LONG).show();
                       Intent intent1 = new Intent(SpecLibro.this, Loginpage.class);
                       startActivity(intent1);
                   }else
                   {
                       Libro libro2 = new Libro(libro.getAutore(), libro.getCodlibro(), libro.getNome(), libro.getAnno(), libro.getGenere(), libro.getUrlimmagine());
                       Intent intent = new Intent(SpecLibro.this, Prenota.class);
                       intent.putExtra(EXTRA_LIBRO2, libro2);
                       startActivity(intent);
                   }
            }
        });
    }
}
