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

    //attributi libro
    private String autore;
    private String codlibro;
    private String nome;
    private String anno;
    private String genere;
    private int giorni;
    private String urlimmagine;

    //getters and setters
    public String getUrlimmagine() {
        return urlimmagine;
    }

    public void setUrlimmagine(String urlimmagine) {
        this.urlimmagine = urlimmagine;
    }

    public int getGiorni() {
        return giorni;
    }

    public void setGiorni(int giorni) {
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
     * @param urlimmagine
     */
    public Libro(String autore, String codlibro, String nome, String anno, String genere, String urlimmagine) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;
        this.urlimmagine=urlimmagine;

    }
    /**
     *
     * @param autore
     * @param codlibro
     * @param nome
     * @param anno
     * @param genere
     * @param urlimmagine
     * @param giorni
     */

    public Libro(String autore, String codlibro, String nome, String anno, String genere, String urlimmagine, int giorni) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;
        this.urlimmagine=urlimmagine;
       this.giorni = giorni;

    }

    //costruttore vuoto
    public Libro() {
    }
}




