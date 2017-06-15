package com.example.lucia.applicazionelab.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lucia.applicazionelab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * Created by Lucia on 20/05/2017.
 * adapter per la listview
 */

public class LibroAdapter extends BaseAdapter  {



    private List<Libro> libri = Collections.emptyList();
    private Context context;
    private DataStore archivio = new DataStore();
private DataStore2 archivio3 = new DataStore2();

    LayoutInflater inflater;

    public LibroAdapter (Context context, List<Libro> libri)
    {
        this.context = context;
        this.libri = libri;
        inflater = LayoutInflater.from(context);



    }
    public class ViewHolder {
        TextView name;
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
        final ViewHolder holder;
        if(convertView ==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.rigalibro, null);

            holder.name = (TextView) convertView.findViewById(R.id.textLibro2);
            convertView.setTag(holder);

            // convertView= LayoutInflater.from(context).inflate(R.layout.rigalibro, parent, false);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(libri.get(position).getNome());




        TextView textLibro = (TextView)convertView.findViewById(R.id.textLibro2);
        TextView textAutore= (TextView)convertView.findViewById(R.id.textAutore2);
        TextView textGenere= (TextView)convertView.findViewById(R.id.textGenere2);
        TextView textAnno= (TextView)convertView.findViewById(R.id.textAnno2);
        TextView textCodLibro= (TextView)convertView.findViewById(R.id.textCodLibro2);
        ImageView ImageCopertina = (ImageView)convertView.findViewById(R.id.ImmagineLibro);



        Libro libro = libri.get(position);
        textLibro.setText(libro.getNome());
        textAutore.setText(libro.getAutore());
        textGenere.setText(libro.getGenere());
        textAnno.setText(libro.getAnno());
        textCodLibro.setText(libro.getCodlibro());
        if (!(archivio3.elencoLibri2().isEmpty())) {
            TextView textGiorni = (TextView)convertView.findViewById(R.id.textPrenotato);
            textGiorni.setText(libro.getGiorni());
        }



        return convertView;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        List <Libro> libritemp = new ArrayList<Libro>(libri);
       libri.clear();
        if (charText.length() == 0) {
            libri.addAll(libritemp);

        } else {
            for (Libro wp : libritemp) {
                if (wp.getNome().toLowerCase(Locale.getDefault()).contains(charText)) {
                    libri.add(wp);
                }
            }
        }
        notifyDataSetChanged();


    }




    }













