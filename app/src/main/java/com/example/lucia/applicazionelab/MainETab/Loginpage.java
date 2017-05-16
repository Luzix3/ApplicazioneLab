package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.applicazionelab.Passworddim.PagePassDim;
import com.example.lucia.applicazionelab.R;
import com.example.lucia.applicazionelab.Registrazione.Page2reg;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * todo: mettere i dati con il database
 *
 *
 */

public class Loginpage extends AppCompatActivity {

    Button btnLogin;
    Button btnRegistrati;
    TextView btnPassword;
    EditText editUser;
    EditText editPassword;
    ProgressBar barra;
    private final static String TAG =  "LoginActivity";
    private final static String TAG2 =  "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

         btnLogin = (Button)findViewById(R.id.buttonLogin);
         btnRegistrati = (Button)findViewById(R.id.buttonRegistrati);
         btnPassword = (TextView)findViewById(R.id.textPassdimenticata);
         editUser = (EditText)findViewById(R.id.EditUsername);
         editPassword = (EditText)findViewById(R.id.editPassword);
         barra = (ProgressBar)findViewById(R.id.barraprogress);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*// definisco l'intenzione di aprire l'Activity "MainActivity.java"
                Intent openMainActivity = new Intent(Loginpage.this, MainActivity.class);
                // passo all'attivazione dell'activity MainActivity.java
                startActivity(openMainActivity);
               */
                String email = editUser.getText().toString();
                String password = editPassword.getText().toString();
                if (email.isEmpty())
                {
                    editUser.setError(getString(R.string.obbligatorio));

                }
                if (password.isEmpty())
                {
                    editPassword.setError(getString(R.string.obbligatorio));
                }

                if(!email.isEmpty() && !password.isEmpty())
                {
                    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    //mi trovo l'unic oggetto per l'autenticazione
                    barra.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    barra.setVisibility(View.INVISIBLE);
                                    //cosa succede quando ho autenticazione
                                    if(task.isSuccessful())
                                    {
                                        Log.d(TAG, "Success");
                                        if(mAuth.getCurrentUser() != null)
                                        {
                                            //mi sono autenticato
                                            //torna allora alla main activity
                                            Toast.makeText(getApplicationContext(),"Sei stato autenticato", Toast.LENGTH_LONG).show();
                                            Intent openMainActivity = new Intent(Loginpage.this, MainActivity.class);
                                            startActivity(openMainActivity);
                                        }
                                        else
                                        {
                                            //devo visualizzare un mex di errore
                                            Toast.makeText(getApplicationContext(),"Username e password non esistenti, registrati!", Toast.LENGTH_LONG).show();
                                        }
                                    }else
                                    {
                                        Log.d(TAG2, "unsuccess");
                                    }
                                }

                            });
                    //è implicitamente asicrona
                    //voglio essere informato cosi metto oncomplete, quando l'autentic è avvenuta
                }
            }

        });


        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPage2reg = new Intent(Loginpage.this, Page2reg.class);
                startActivity(openPage2reg);
            }
        });


        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagePassDim = new Intent(Loginpage.this, PagePassDim.class);
                startActivity(openPagePassDim);

            }
        });


    }



}
