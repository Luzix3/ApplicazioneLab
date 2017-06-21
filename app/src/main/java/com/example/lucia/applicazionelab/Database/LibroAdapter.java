package com.example.lucia.applicazionelab.Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.lucia.applicazionelab.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.support.design.R.id.image;


/**
 * Created by Lucia on 20/05/2017.
 * adapter per la listview
 */

public class LibroAdapter extends BaseAdapter  {
    //reference dello storage
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    //lista libri: collezione di libri
    private List<Libro> libri = Collections.emptyList();
    private Context context;
    //oggetto datastore
    private DataStore archivio = new DataStore();
    LayoutInflater inflater;
    //costanti
    private final static String KEY_IMMAGINE    = "urlimmagine";

    //costruttore
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
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(libri.get(position).getNome());


        TextView textLibro = (TextView)convertView.findViewById(R.id.textLibro2);
        TextView textAutore= (TextView)convertView.findViewById(R.id.textAutore2);
        TextView textGenere= (TextView)convertView.findViewById(R.id.textGenere2);
        TextView textAnno= (TextView)convertView.findViewById(R.id.textAnno2);
        TextView textCodLibro= (TextView)convertView.findViewById(R.id.textCodLibro2);
        ImageView ImageCopertina = (ImageView)convertView.findViewById(R.id.ImmagineCopertina2);

        //metto i parametri nell'oggetto libro per poi farli comparire nella listview
        Libro libro = libri.get(position);
        textLibro.setText(libro.getNome());
        textAutore.setText(libro.getAutore());
        textGenere.setText(libro.getGenere());
        textAnno.setText(libro.getAnno());
        textCodLibro.setText(libro.getCodlibro());
        //carico immagine dal metodo creato prima attraverso url
        //uso picasso che è una libreria trovata su github, essenzialmente carica, dato un context, un url in una immagine
        //gestisce si amemoria che cache
        Picasso.with(context).load(libro.getUrlimmagine()).fit().into(ImageCopertina);

        if (!(archivio.elencoPrenotazioni().isEmpty())) {
            TextView textGiorni = (TextView)convertView.findViewById(R.id.textPrenotato);
            textGiorni.setText(libro.getGiorni());
            TextView textConsegna = (TextView)convertView.findViewById(R.id.textConsegna);
            textConsegna.setText(libro.getDataconsegna());
        }
        return convertView;
    }


    // Filtra quando viene digitato un char
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List <Libro> libritemp = new ArrayList<Libro>(libri);
        if (charText.length() == 0) {
            libritemp.addAll(libri);
        } else {
            for (Libro libro : libri) {
                if (libro.getNome().toLowerCase(Locale.getDefault()).contains(charText)) {
                    libritemp.add(libro);
                }
            }
        }
        notifyDataSetChanged();
    }
    }