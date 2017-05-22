package com.example.lucia.applicazionelab.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lucia.applicazionelab.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lucia on 20/05/2017.
 * adapter per la listview
 */

public class LibroAdapter extends BaseAdapter {


    private List<Libro> libri = Collections.emptyList();
    private Context context;

    public LibroAdapter (Context context)
    {
        this.context = context;

    }


    public void update(List<Libro> newList)
    {
        libri = newList;
        notifyDataSetChanged();

    }




    //la listview chiederà all'adapter qunti elementi deve visualizzare
    @Override
    public int getCount() {
        return libri.size();
    }

    //
    @Override
    public Libro getItem(int position) {
        return libri.get(position);
    }

    //
    @Override
    public long getItemId(int position) {
        return 0;
    }



    //chiede all'adapter la view da visualizzare in quel punto
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView== null)

            convertView= LayoutInflater.from(context).inflate(R.layout.rigalibro, parent, false);

        TextView textLibro = (TextView)convertView.findViewById(R.id.textLibro2);
        TextView textAutore= (TextView)convertView.findViewById(R.id.textAutore2);
        TextView textGenere= (TextView)convertView.findViewById(R.id.textGenere2);
        TextView textAnno= (TextView)convertView.findViewById(R.id.textAnno2);
        TextView textCodLibro= (TextView)convertView.findViewById(R.id.textCodLibro2);
        // ImageView ImageCopertina = (ImageView)convertView.findViewById(R.id.ImmagineLibro);

        Libro libro = libri.get(position);
        textLibro.setText(libro.getNome());
        textAutore.setText(libro.getAutore());
        textGenere.setText(libro.getGenere());
        textAnno.setText(libro.getAnno());
        textCodLibro.setText(libro.getCodlibro());
        // ImageCopertina.setImageResource(R.drawable.image_bg);

        return convertView;
    }







}
