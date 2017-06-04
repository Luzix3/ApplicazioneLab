package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.R;

import org.w3c.dom.Text;

public class DettagliMioLibro extends AppCompatActivity {

    //widget
    private TextView mNome;
    private TextView mPrenotato;
    private TextView mConsegna;
    private TextView mTorna;

    private final static String EXTRA_LIBRO2 = "libro2";
    private final static String EXTRA_PERIODO = "periodo prenotazione";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_mio_libro);

        mNome = (TextView)findViewById(R.id.textNome2);
        mPrenotato = (TextView)findViewById(R.id.textPrenotato);
        mConsegna= (TextView)findViewById(R.id.textConsegna);
        mTorna= (TextView)findViewById(R.id.textTorna);

        Intent intent = getIntent();
        final Libro libro = (Libro)intent.getSerializableExtra(EXTRA_LIBRO2);




        if (libro != null) {
            mNome.setText(libro.getNome());

            Intent intent2 = getIntent();
            Bundle bd = intent.getExtras();
            if(bd != null) {
                String periodo =(String) bd.getString(EXTRA_PERIODO);
                mPrenotato.setText(periodo);
            }


                mConsegna.setText(" ");




        }

        mTorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent ( DettagliMioLibro.this, MainActivity.class);
                startActivity(intent1);
            }
        });







    }
}
