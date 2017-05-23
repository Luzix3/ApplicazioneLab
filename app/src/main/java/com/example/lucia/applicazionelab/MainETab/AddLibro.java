package com.example.lucia.applicazionelab.MainETab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.R;
import com.example.lucia.applicazionelab.Registrazione.Page2reg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
@SuppressWarnings("deprecation")
public class AddLibro extends ActionBarActivity {

    Button btnAdd;
    EditText Editname;
    EditText EditAutor;
    EditText EditAnn;
    EditText EditCode;
    EditText EditTrama;
    EditText EditGener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);


        btnAdd = (Button)findViewById(R.id.buttonProcedi3);
        Editname = (EditText)findViewById(R.id.editName);
        EditAutor = (EditText)findViewById(R.id.editAutor);
        EditAnn = (EditText)findViewById(R.id.editAnn);
        EditCode = (EditText)findViewById(R.id.editCode);
        EditTrama = (EditText)findViewById(R.id.editTrama);
        EditGener = (EditText)findViewById(R.id.editGener);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name1 = Editname.getText().toString();
                final String autor1 = EditAutor.getText().toString();
                final String gener1 = EditGener.getText().toString();
                final String code = EditCode.getText().toString();
                final String anno1 = EditAnn.getText().toString();


                if (anno1.isEmpty()) {
                    EditAnn.setError(getString(R.string.obbligatorio));

                }


                if (name1.isEmpty()) {
                    Editname.setError(getString(R.string.obbligatorio));

                }

                if (autor1.isEmpty()) {
                    EditAutor.setError(getString(R.string.obbligatorio));

                }

                if (gener1.isEmpty()) {
                    EditGener.setError(getString(R.string.obbligatorio));

                }

                if (code.isEmpty()) {
                    EditCode.setError(getString(R.string.obbligatorio));

                }

                if (!name1.isEmpty() && !autor1.isEmpty() && !gener1.isEmpty() && !code.isEmpty() && !anno1.isEmpty())
                {




                    DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("libri");
                    //ora creo un nodo user che mi ritorna la chiave unica dell'user (quella che solo questa app può avere

                    String BookId = mDatabase1.push().getKey();
                    // creo un oggetto della classe utente
                    Libro l1 = new Libro(autor1, code, name1, anno1, gener1);
                    // metto l'user nel database sotto il nodo Utenti con la sua chiave unica
                    mDatabase1.child(BookId).setValue(l1);

                    Intent openPageSucces2= new Intent(AddLibro.this, AddLibroSuccess.class);
                    startActivity(openPageSucces2);






                }
            }


        });















                }


            }