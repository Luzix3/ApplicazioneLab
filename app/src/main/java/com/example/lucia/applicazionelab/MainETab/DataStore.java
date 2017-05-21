package com.example.lucia.applicazionelab.MainETab;

import com.example.lucia.applicazionelab.Database.Libro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 classe che rappresenta il datamodel
 * Created by Lucia on 20/05/17.
 */

public class DataStore {

    // Costanti
    private final static String TAG = "DataStore";
    private final static String DB_LIBRI = "libri";
    private final static String KEY_AUTORE = "autore";
    private final static String KEY_NOME = "nome";
    private final static String KEY_GENERE = "genere";
    private final static String KEY_ANNO = "anno";

    private ValueEventListener listenerLibri;

    // Lista locale degli studenti
    // Todo: da eliminare
    private ArrayList<Libro> libri;

    /**
     * Costruttore
     */
    public DataStore() {
        libri = new ArrayList<>();
    }

    public interface UpdateListener {
        void libriAggiornati();
    }

    public void iniziaOsservazioneLibri (final UpdateListener notifica) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(DB_LIBRI);

        listenerLibri = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libri.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Libro libro = new Libro();
                    libro.setCodlibro(elemento.getKey());
                    libro.setNome(elemento.child(KEY_NOME).getValue(String.class));
                    libro.setAutore(elemento.child(KEY_AUTORE).getValue(String.class));
                    libro.setGenere(elemento.child(KEY_GENERE).getValue(String.class));
                    libro.setAnno(elemento.child(KEY_ANNO).getValue(String.class));
                    libri.add(libro);
                }
                notifica.libriAggiornati();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(listenerLibri);
    }

    public void terminaOsservazioneLibri() {
        if (listenerLibri != null)
            FirebaseDatabase.getInstance().getReference(DB_LIBRI).removeEventListener(listenerLibri);
    }



    /**
     * Aggiunge un libro al database
     * @param libro
     */
    public void aggiungiLibro(Libro libro) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DB_LIBRI).child(libro.getCodlibro());
        ref.setValue(libro);
        /* ref.child(KEY_COGNOME).setValue(studente.getCognome());
        ref.child(KEY_NOME).setValue(studente.getNome());
        ref.child(KEY_CREDITI).setValue(studente.getCrediti());
        */
    }

    /**
     * aggiorna i dati del libro impostando il codice libro come parametro identificativo
     * @param libro dati da aggiornare
     */
    public void aggiornaLibro(Libro libro) {
        int posizione = getLibroIndex(libro.getCodlibro());
        if (posizione == -1)
            aggiungiLibro(libro);
        else
            libri.set(posizione, libro);
    }

    /**
     * Elimina un libro
     * @param codlibro dello studente da eliminare
     */
    public void eliminaLibro(String codlibro) {
        int posizione = getLibroIndex(codlibro);
        if (posizione != -1)
            libri.remove(posizione);
    }

    /**
     * Legge il libro dal codice libro passato
     * @param codlibro codlibro da cercare
     * @return Libro letto, oppure null nel caso non venga trovato
     */
    public Libro leggiLibro(String codlibro) {
        int posizione = getLibroIndex(codlibro);
        if (posizione == -1)
            return null;
        else
            return libri.get(posizione);
    }

    /**
     * Ottiene l'elenco di tutti i libri
     * Todo: Attenzione il metodo è potenzialmente pericoloso. Potrebbe restituire troppi dati!
     * @return Lista di libri
     */
    public List<Libro> elencoLibri() {
        return libri;
    }

    /**
     * Restituisce il numero di libri presenti nel database
     * @return numero di libri
     */
    public int numeroLibri() {
        return libri.size();
    }

    /**
     * Restituisce l'indice di un libro nell'array partendo dal codice libro
     * @param codlibro codice del libro da cercare da cercare
     * @return indice del libro. -1 se non trovato
     */
    private int getLibroIndex(String codlibro) {
        boolean trovato = false;
        int index = 0;
        while (!trovato && index < libri.size()) {
            if (libri.get(index).getCodlibro().equals(codlibro)) {
                return index;
            }
            ++index;
        }
        return -1;
    }
}
