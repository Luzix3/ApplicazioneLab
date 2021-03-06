package com.example.lucia.applicazionelab.Database;

import java.io.Serializable;

/**
 * Created by Lucia on 18/05/2017.
 */

public class Utente implements Serializable{
    //attributi utente
    private String email;
    private String password;
    private String cellulare;
    private String libro;
    private String codlibro;

    //getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getCodlibro() {
        return codlibro;
    }

    public void setCodlibro(String codlibro) {
        this.codlibro = codlibro;
    }

    //costruttore vuoto
    public Utente() {
    }

    public Utente (String email, String password, String cellulare)
    {
       this.email=email;
        this.password = password;
        this.cellulare=cellulare;
    }
    public Utente (String libro, String codlibro)
    {
        this.libro= libro;
        this.codlibro = codlibro;
    }
}
