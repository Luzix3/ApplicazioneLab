package com.example.lucia.applicazionelab.Registrazione;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Database.Utente;
import com.example.lucia.applicazionelab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("deprecation")
public class Page2reg extends ActionBarActivity {

    Button btnReg;
    EditText Editemail;
    EditText Editpass;
    CheckBox Cutente;
    CheckBox Cgestore;
    CheckBox Ccondizioni;
    EditText Editconfpass;

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2reg);

        btnReg = (Button) findViewById(R.id.buttonAvanti);
        Editemail = (EditText) findViewById(R.id.editRegEmail);
        Editpass = (EditText) findViewById(R.id.editRegPass);
        Cutente = (CheckBox) findViewById(R.id.checkBoxCliente);
        Cgestore = (CheckBox) findViewById(R.id.checkBoxGestore);
        Ccondizioni = (CheckBox) findViewById(R.id.checkBoxCondizioni);
        Editconfpass = (EditText) findViewById(R.id.editConfPass);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = false;
                final String email2 = Editemail.getText().toString();
                final String password2 = Editpass.getText().toString();
                String confpass = Editconfpass.getText().toString();

                if (email2.isEmpty()) {
                    Editemail.setError(getString(R.string.obbligatorio));
                }
                if (password2.isEmpty()) {
                    Editpass.setError(getString(R.string.obbligatorio));
                }

                if (confpass.isEmpty()) {
                    Editconfpass.setError(getString(R.string.obbligatorio));
                }

                if (Cgestore.isChecked()) {
                    Cgestore.setError(getString(R.string.errorecasella));
                }

                if (!(Cutente.isChecked())) {
                    Cutente.setError(getString(R.string.obbligatorio));
                }

                if (!(Ccondizioni.isChecked())) {
                    Ccondizioni.setError(getString(R.string.obbligatorio));
                }

                if (!(isEmailValid(email2))) {
                    Editemail.setError(getString(R.string.emailvalida));
                }


                if (password2.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password troppo corta, deve essere di almeno 6 caratteri!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(password2.equals(confpass))) {
                    Editconfpass.setError(getString(R.string.passuguali));

                } else {
                    a = true;
                }


                if (password2.length() > 6 && !email2.isEmpty() && a == true && !password2.isEmpty() && isEmailValid(email2) && Ccondizioni.isChecked() && Cutente.isChecked() && !(Cgestore.isChecked())) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final FirebaseAuth mAuth2 = FirebaseAuth.getInstance();

                    mAuth2.createUserWithEmailAndPassword(email2, password2)
                            .addOnCompleteListener(Page2reg.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Page2reg.this, "Creazione utente: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    // Se la registrazione fallisce, mostra un messaggio.Se invece va a buon fine
                                    // si passa alla pagina di conferma
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Page2reg.this, "Registrazione fallita" + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(Page2reg.this, RegSuccess.class));
                                        finish();
                                    }
                                }
                            });
                }
            }


        });
    }
}