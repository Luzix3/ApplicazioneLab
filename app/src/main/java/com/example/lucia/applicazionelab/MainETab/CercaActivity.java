package com.example.lucia.applicazionelab.MainETab;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.SearchManager;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;

import android.widget.TextView;

import com.example.lucia.applicazionelab.Database.Libro;
import com.example.lucia.applicazionelab.Database.LibroAdapter;
import com.example.lucia.applicazionelab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CercaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {



    // Costanti
    private final static String EXTRA_LIBRO = "libro";
    private final static String TAG = "BiblApp";

    // Widget
    private ListView listaLibri;

    private DataStore archivio = new DataStore();

    // Adapter
    private LibroAdapter adapter;

      // Autenticazione Firebase
    private FirebaseAuth mAuth5;
    ArrayList<Libro> arraylist = new ArrayList<Libro>();



    //searchbar
    SearchView editsearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cerca);



       final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);

        swipeLayout.setEnabled(false);





        // Autenticazione Firebase
        mAuth5 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user4 = mAuth5.getCurrentUser();
        if (user4 == null) {
            // Utente non autenticato, voglio impedire l'accesso
            //devo impedirlo o meno?
            //todo: vedi bene questa riga
            /*
            Intent intent = new Intent(this, Loginpage.class);
            startActivity(intent);
            */
        } else {
            // Utente autenticato.
            // Nessuna operazione richiesta
        }

        listaLibri = (ListView)findViewById(R.id.ListaLibri);
        adapter = new LibroAdapter(this,arraylist );

        archivio.iniziaOsservazioneLibri(new DataStore.UpdateListener() {
            @Override
            public void libriAggiornati() {


               adapter.update(archivio.elencoLibri());
            }
        });

        listaLibri.setAdapter(adapter);
        listaLibri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), SpecLibro.class);
                intent.putExtra(EXTRA_LIBRO, libro);
                startActivity(intent);
            }
        });

        Libro l = new Libro();
        l.setCodlibro("0000001");
        l.setNome("Harry Potter 1");
        l.setAutore("j.k. rowling");
        l.setGenere("Fantasy");
        l.setAnno("2002");
        archivio.aggiungiLibro(l);




        TextView textAddLibro = (TextView)findViewById(R.id.textAddLibro);

        textAddLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPageAdd = new Intent(CercaActivity.this, AddLibro.class);
                startActivity(openPageAdd);
            }
        });



        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 3000);
            }


        });



        listaLibri.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    swipeLayout.setEnabled(true);
                else
                    swipeLayout.setEnabled(false);
            }
        });



        editsearch = (SearchView) findViewById(R.id.searchView);
        editsearch.setOnQueryTextListener(this);


    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneLibri();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }





    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}

