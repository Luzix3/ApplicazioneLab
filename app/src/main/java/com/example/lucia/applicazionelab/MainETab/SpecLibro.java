package com.example.lucia.applicazionelab.MainETab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressWarnings("deprecation")

public class SpecLibro extends ActionBarActivity {

    // Costanti
    private final static String EXTRA_LIBRO = "libro";

    // Widget
    private TextView mCodLibro;
    private TextView mLibro;
    private TextView mAutore;
    private TextView mGenere;
    private TextView mAnno;
    private ImageView mImageLibro;
    TextView prenota;
    private FirebaseAuth mauth;


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
        // mImageLibro = (ImageView)findViewById(R.id.ImmagineLibro);

        // Ottengo i dati passati ed eventualmente visualizzo il libro
        Intent intent = getIntent();
        Libro libro = (Libro)intent.getSerializableExtra(EXTRA_LIBRO);

        if (libro != null) {
            mCodLibro.setText(libro.getCodlibro());
            mAutore.setText(libro.getAutore());
            mLibro.setText(libro.getNome());
            mAnno.setText(libro.getAnno());
            mGenere.setText(libro.getGenere());
            // mImageLibro.setImageResource(R.drawable.image_bg);

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
                       Intent intent1 = new Intent(SpecLibro.this, Prenota.class);

                       startActivity(intent1);
                   }

            }
        });


    }
}
