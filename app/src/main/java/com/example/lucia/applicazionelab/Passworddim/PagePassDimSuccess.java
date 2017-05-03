package com.example.lucia.applicazionelab.Passworddim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucia.applicazionelab.MainActivity;
import com.example.lucia.applicazionelab.R;

public class PagePassDimSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pass_dim_success);


        Button btnTornaLogin = (Button)findViewById(R.id.buttonTornaLog);

        btnTornaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPageLog = new Intent(PagePassDimSuccess.this,MainActivity.class);
                startActivity(openPageLog);
            }
        });

    }
}
