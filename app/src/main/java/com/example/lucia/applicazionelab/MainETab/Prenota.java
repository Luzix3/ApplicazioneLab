package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.Utente;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Prenota extends AppCompatActivity {
    private final static String EXTRA_LIBRO2 = "libro2";
    private final static String EXTRA_NOME = "nome libro";
    private final static String EXTRA_AUTORE = "autore";
    private final static String EXTRA_CODICE = "codice";
    private final static String EXTRA_GENERE = "genere";
    private final static String EXTRA_ANNO = "anno";
    private final static String EXTRA_PRENOTAZIONE = "prenotazioni";
    private final static String EXTRA_IMMAGINE  = "url immagine";
    CheckBox Unasettimana;
    CheckBox Duesettimane;
    CheckBox Ventigiorni;
    String periodo;
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
        final  Libro libro2 = (Libro)intent1.getSerializableExtra(EXTRA_LIBRO2);

        mAuth6 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        final FirebaseUser user6 = mAuth6.getCurrentUser();

        Unasettimana = (CheckBox)findViewById(R.id.check1week);
        Duesettimane = (CheckBox)findViewById(R.id.check2weeks);
        Ventigiorni = (CheckBox)findViewById(R.id.check20gg);

        prenota = (Button)findViewById(R.id.ButtonPrenota);
        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (Unasettimana.isChecked() || Duesettimane.isChecked() || Ventigiorni.isChecked()) {
                     if(Unasettimana.isChecked())
                     {
                         libro2.setGiorni(7);
                     }
                     if(Duesettimane.isChecked())
                     {
                         libro2.setGiorni(15);
                     }
                     if(Ventigiorni.isChecked())
                     {
                         libro2.setGiorni(20);
                     }

                     FirebaseDatabase database = FirebaseDatabase.getInstance();
                     DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Utenti");
                     DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                     String dataoggi = df.format(Calendar.getInstance().getTime());
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Calendar c = Calendar.getInstance();
                     try {
                         c.setTime(sdf.parse(dataoggi));
                     } catch (ParseException e) {
                         e.printStackTrace();
                     }
                     c.add(Calendar.DATE, libro2.getGiorni());
                     sdf = new SimpleDateFormat("dd/MM/yyyy");
                     Date datarisultante = new Date(c.getTimeInMillis());
                     libro2.setDataconsegna(sdf.format(datarisultante));
                     Libro libro1 = new Libro(libro2.getAutore(), libro2.getCodlibro(), libro2.getNome(), libro2.getAnno(), libro2.getGenere(),libro2.getUrlimmagine() ,libro2.getGiorni(), libro2.getDataconsegna());
                     ref.child(user6.getUid()).child(EXTRA_PRENOTAZIONE).push().setValue(libro1);
                     Intent intent1 = new Intent(Prenota.this, MainActivity.class);
                     intent1.putExtra(EXTRA_LIBRO2, libro1);
                     Toast.makeText(getApplicationContext(), "Prenotazione avvenuta con successo!" +
                             "Ritira il libro nelle prossime 24 ore, altrimenti la prenotazione verrà cancellata.", Toast.LENGTH_LONG).show();
                     startActivity(intent1);
                 }else
                 {
                     Toast.makeText(getApplicationContext(), "Devi selezionare un periodo di tempo se vuoi prenotare il libro!", Toast.LENGTH_LONG).show();
                 }
            }
        });
    }
}
