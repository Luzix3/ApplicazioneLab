package com.example.lucia.applicazionelab.Passworddim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.applicazionelab.MainETab.Loginpage;
import com.example.lucia.applicazionelab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PagePassDim extends AppCompatActivity {

    EditText editEmailp;
    Button btnProcedi;
    TextView textbacklog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pass_dim);


        editEmailp = (EditText)findViewById(R.id.editPassEmail);
        btnProcedi = (Button)findViewById(R.id.buttonProcedi);
        textbacklog = (TextView)findViewById(R.id.textBackLog);

        final FirebaseAuth mAuth3 = FirebaseAuth.getInstance();

        textbacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagelogback = new Intent(PagePassDim.this,Loginpage.class);
                startActivity(openPagelogback);
            }
        });


        btnProcedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email3 = editEmailp.getText().toString();

                if (email3.isEmpty())
                {
                    editEmailp.setError(getString(R.string.obbligatorio));

                }






                mAuth3.sendPasswordResetEmail(email3)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PagePassDim.this, "Ti abbiamo inviato una mail per resettare la password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PagePassDim.this, "Procedura non riuscita, controlla i dati o la tua connessione.", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });



            }
        });

    }
}
