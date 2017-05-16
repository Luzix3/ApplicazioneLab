package com.example.lucia.applicazionelab.Registrazione;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.lucia.applicazionelab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Page2reg extends Activity {

    Button btnReg;
    EditText Editemail;
    EditText Editpass;
    CheckBox Cutente;
    CheckBox Cgestore;
    CheckBox Ccondizioni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2reg);


        btnReg = (Button)findViewById(R.id.buttonAvanti);
        Editemail = (EditText)findViewById(R.id.editRegEmail);
        Editpass = (EditText)findViewById(R.id.editRegPass);
        Cutente = (CheckBox)findViewById(R.id.checkBoxCliente);
        Cgestore = (CheckBox)findViewById(R.id.checkBoxGestore);
        Ccondizioni= (CheckBox)findViewById(R.id.checkBoxCondizioni);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email2 = Editemail.getText().toString();
                String password2 = Editpass.getText().toString();
                if (email2.isEmpty())
                {
                    Editemail.setError(getString(R.string.obbligatorio));

                }
                if (password2.isEmpty())
                {
                    Editpass.setError(getString(R.string.obbligatorio));
                }



                if (Cgestore.isChecked())
                {
                    Cgestore.setError(getString(R.string.errorecasella));
                }

                if (!(Cutente.isChecked()) )
                {
                    Cutente.setError(getString(R.string.obbligatorio));
                }

                if (!(Ccondizioni.isChecked()) )
                {
                    Ccondizioni.setError(getString(R.string.obbligatorio));
                }
                // todo: mettere caratteri obbligatori alla password e mettere emailvalida

                if(!email2.isEmpty() && !password2.isEmpty())
                {
                    // todo: creare oggetto daabase
                    //problema qui!!
                    Intent openPageRegSucc = new Intent(Page2reg.this, RegSuccess.class);
                    startActivity(openPageRegSucc);
                }

             }
        });




    }




}
