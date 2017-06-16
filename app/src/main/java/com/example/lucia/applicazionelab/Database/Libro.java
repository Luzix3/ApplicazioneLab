package com.example.lucia.applicazionelab.Database;

import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Lucia on 20/05/2017.
 * classe creata per descrivere le caratteristiche dei libri
 */

public class Libro implements Serializable{
    private String autore;
    private String codlibro;
    private String nome;
    private String anno;
    private String genere;
    private String giorni;
    private ImageView imagepath;
    private Uri immagine;



    //mettere private cosi si nasconde l'implementazione della classe
    //aggiunge getter e setter
    //mancano i costruttori


    public Uri getImmagine() {
        return immagine;
    }

    public void setImmagine(Uri immagine) {
        this.immagine = immagine;
    }

    public ImageView getImagepath() {
        return imagepath;
    }

    public void setImagepath(ImageView imagepath) {
        this.imagepath = imagepath;
    }

    public String getGiorni() {
        return giorni;
    }

    public void setGiorni(String giorni) {
        this.giorni = giorni;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCodlibro() {
        return codlibro;
    }

    public void setCodlibro(String codlibro) {
        this.codlibro = codlibro;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    /**
     *
     * @param autore
     * @param codlibro
     * @param nome
     * @param anno
     * @param genere
     *
     */
    public Libro(String autore, String codlibro, String nome, String anno, String genere, Uri immagine) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;
        this.immagine=immagine;

    }

    public Libro(String autore, String codlibro, String nome, String anno, String genere) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;

    }


    public Libro(String autore, String codlibro, String nome, String anno, String genere, Uri immagine, String giorni) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;
        this.immagine=immagine;
       this.giorni = giorni;

    }

    public Libro(String autore, String codlibro, String nome, String anno, String genere, String giorni) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;

        this.giorni = giorni;

    }

    //puoservire anche il costruttore vuoto, cosi posso creare un oggetto libro nuovo


    public Libro() {


    }
}




