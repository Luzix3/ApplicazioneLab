package com.example.lucia.applicazionelab.Database;

import java.io.Serializable;

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

    //mettere private cosi si nasconde l'implementazione della classe
    //aggiunge getter e setter
    //mancano i costruttori


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
     */
    public Libro(String autore, String codlibro, String nome, String anno, String genere) {
        this.autore = autore;
        this.codlibro=codlibro;
        this.nome = nome;
        this.anno=anno;
        this.genere=genere;

    }

    //puoservire anche il costruttore vuoto, cosi posso creare un oggetto libro nuovo


    public Libro() {


    }
}




