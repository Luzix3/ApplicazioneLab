package com.example.lucia.applicazionelab.Database;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by Lucia on 20/05/2017.
 * classe creata per descrivere le caratteristiche dei libri
 */

public class Libro implements Serializable{
    private String autore;
    private String codlibro;
    private String libro;
    private String anno;
    private String genere;
    private Image copertina;

    //mettere private cosi si nasconde l'implementazione della classe
    //aggiunge getter e setter
    //mancano i costruttori


    public Image getCopertina() {
        return copertina;
    }

    public void setCopertina(Image copertina) {
        this.copertina = copertina;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
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
     * @param libro
     * @param anno
     * @param genere
     * @param copertina
     */
    public Libro(String autore, String codlibro, String libro, String anno, String genere, Image copertina) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.libro = libro;
        this.anno=anno;
        this.genere=genere;
        this.copertina=copertina;

    }

    //puoservire anche il costruttore vuoto, cosi posso creare un oggetto libro nuovo


    public Libro() {


    }
}




