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

Button prenota;
    // Autenticazione Firebase
    private FirebaseAuth mAuth6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota);
        // Autenticazione Firebase


        mAuth6 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        final FirebaseUser user6 = mAuth6.getCurrentUser();

        Intent intent = getIntent();
        final String libro2 = (String)intent.getSerializableExtra(EXTRA_LIBRO2);

        prenota = (Button)findViewById(R.id.ButtonPrenota);
        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Miei Libri");

                ref.child(user6.getUid()).push().setValue("andonio");






            }
        });


    }
}
