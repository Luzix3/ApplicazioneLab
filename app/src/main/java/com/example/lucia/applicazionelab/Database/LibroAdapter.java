package com.example.lucia.applicazionelab.Database;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lucia.applicazionelab.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lucia on 20/05/2017.
 * adapter per la listview
 */

public class LibroAdapter extends BaseAdapter {

    private Context context;
    private List<Libro> lista = Collections.emptyList();

    //la listview chiederà all'adapter qunti elementi deve visualizzare
    @Override
    public int getCount() {
        return lista.size();
    }

    //chiede all'adapter la view da visualizzare in quel punto
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView== null)

            convertView= LayoutInflater.from(context).inflate(R.layout.rigalibro, parent, false);

        TextView textLibro = (TextView)convertView.findViewById(R.id.textLibro);
        TextView textAutore= (TextView)convertView.findViewById(R.id.textAutore);
        TextView textGenere= (TextView)convertView.findViewById(R.id.textGenere);
        TextView textAnno= (TextView)convertView.findViewById(R.id.textAnno);
        TextView textCodLibro= (TextView)convertView.findViewById(R.id.textCodLibro);
        ImageView ImageCopertina = (ImageView)convertView.findViewById(R.id.ImmagineCopertina);

        Libro libro = lista.get(position);
        textLibro.setText(libro.getLibro());
        textAutore.setText(libro.getAutore());
        textGenere.setText(libro.getGenere());
        textAnno.setText(libro.getAnno());
        textCodLibro.setText(libro.getCodlibro());
        ImageCopertina.setImageResource(R.drawable.image_bg);

        return null;
    }

    //
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //data la posizione prende l'elemento a quella posizione e cioè il libro e lo restituisce
    @Override
    public Libro getItem(int position) {
        return lista.get(position);
    }

    public LibroAdapter (Context context)
    {
        this.context = context;

    }

    public void updateData(List<Libro> libri)
    {
        lista=libri;

    }



}
