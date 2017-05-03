package com.example.lucia.applicazionelab.Passworddim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucia.applicazionelab.R;

public class PagePassDim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pass_dim);


        Button btnProcedi = (Button)findViewById(R.id.buttonProcedi);

        btnProcedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPagePassDimSuccess = new Intent(PagePassDim.this,PagePassDimSuccess.class);
                startActivity(openPagePassDimSuccess);
            }
        });

    }
}
