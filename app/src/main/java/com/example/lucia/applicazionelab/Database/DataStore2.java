package com.example.lucia.applicazionelab.Database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class DataStore2 {

    // Costanti
    private final static String TAG = "DataStore";
    private final static String DB_MIEILIBRI = "Miei Libri";
    private final static String KEY_AUTORE = "autore";
    private final static String KEY_NOME = "nome";
    private final static String KEY_GENERE = "genere";
    private final static String KEY_ANNO = "anno";
    private final static String KEY_CODLIBRO = "codice libro";
    private final static String KEY_GIORNI = "giorni prenotazione";


    private ValueEventListener listenerLibri;
    private FirebaseAuth mAuth7;

    // Lista locale dei libri
    // Todo: da eliminare
    private ArrayList<Libro> libri;

    /**
     * Costruttore
     */
    public DataStore2() {
        libri = new ArrayList<>();
    }

    public interface UpdateListener2 {
        void libriAggiornati();
    }

    public void iniziaOsservazioneLibri2 (final DataStore.UpdateListener notifica) {

        mAuth7 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user5 = mAuth7.getCurrentUser();
        String user7 = user5.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(DB_MIEILIBRI).child(user7);


        listenerLibri = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libri.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Libro libro = new Libro();

                    libro.setCodlibro(elemento.child(KEY_CODLIBRO).getValue(String.class));
                    libro.setNome(elemento.child(KEY_NOME).getValue(String.class));
                    libro.setAutore(elemento.child(KEY_AUTORE).getValue(String.class));
                    libro.setGenere(elemento.child(KEY_GENERE).getValue(String.class));
                    libro.setAnno(elemento.child(KEY_ANNO).getValue(String.class));
                    libro.setGiorni(elemento.child(KEY_GIORNI).getValue(String.class));



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


    public void terminaOsservazioneLibri2() {
        if (listenerLibri != null)
            mAuth7 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user5 = mAuth7.getCurrentUser();
        String user7 = user5.getUid();
            FirebaseDatabase.getInstance().getReference(DB_MIEILIBRI).child(user7).removeEventListener(listenerLibri);
    }



    /**
     * Aggiunge un libro al database
     * @param libro
     */
    public void aggiungiLibro2(Libro libro) {
        mAuth7 = FirebaseAuth.getInstance();

        // Comportamento differenziato
        FirebaseUser user5 = mAuth7.getCurrentUser();
        String user7 = user5.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DB_MIEILIBRI).child(user7).child(libro.getCodlibro());
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
    public void aggiornaLibro2(Libro libro) {
        int posizione = getLibroIndex(libro.getCodlibro());
        if (posizione == -1)
            aggiungiLibro2(libro);
        else
            libri.set(posizione, libro);
    }

    /**
     * Elimina un libro
     * @param codlibro dello studente da eliminare
     */
    public void eliminaLibro2(String codlibro) {
        int posizione = getLibroIndex(codlibro);
        if (posizione != -1)
            libri.remove(posizione);
    }

    /**
     * Legge il libro dal codice libro passato
     * @param codlibro codlibro da cercare
     * @return Libro letto, oppure null nel caso non venga trovato
     */
    public Libro leggiLibro2(String codlibro) {
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
    public List<Libro> elencoLibri2() {
        return libri;
    }

    /**
     * Restituisce il numero di libri presenti nel database
     * @return numero di libri
     */
    public int numeroLibri2() {
        return libri.size();
    }

    /**
     * Restituisce l'indice di un libro nell'array partendo dal codice libro
     * @param codlibro codice del libro da cercare
     ** @return indice del libro. -1 se non trovato
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
