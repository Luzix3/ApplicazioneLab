package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.R;

public class SpecLibro extends AppCompatActivity {

    // Costanti
    private final static String EXTRA_LIBRO = "libro";

    // Widget
    private TextView mCodLibro;
    private TextView mNome;
    private TextView mAutore;
    private TextView mGenere;
    private TextView mAnno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_libro);

        // Imposto gli id widget
       mCodLibro = (TextView)findViewById(R.id.textCodLibro);
        mAutore = (TextView)findViewById(R.id.textAutore);
        mNome = (TextView)findViewById(R.id.textLibro);
        mGenere = (TextView)findViewById(R.id.textGenere);
        mAnno= (TextView)findViewById(R.id.textAnno);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Libro libro = (Libro)intent.getSerializableExtra(EXTRA_LIBRO);

        if (libro != null) {
            mCodLibro.setText(libro.getCodlibro());
            mAutore.setText(libro.getAutore());
            mNome.setText(libro.getNome());
            mAnno.setText(libro.getAnno());
            mGenere.setText(libro.getGenere());

        }
    }
}
