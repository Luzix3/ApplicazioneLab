package com.example.lucia.applicazionelab.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe che gestisce il database della biblioteca
 * Created by Lucia on 08/05/2017.
 */

public class MyDatabase {
    SQLiteDatabase mDb;
    DbHelper mDbHelper;
    Context mContext;
    private static final String DB_NAME= "Database utenti";
    private static final int DB_VERSION=1; //numero di versione database

    public MyDatabase(Context ctx)
    {
        mContext= ctx;
        mDbHelper= new DbHelper(ctx, DB_NAME, null, DB_VERSION); //istanziando la classe istanziamo anche l'helper
    }

    public void open()
    {
        mDb=mDbHelper.getWritableDatabase();

    }

    public void Close()
    {
        mDb.close();
    }

    public void insertData(String utente, String username, String password, String email, String cellulare, String codice)
    {
        ContentValues cv = new ContentValues();
        cv.put(DatiMetaData.DATI_UTENTE_KEY, utente);
        cv.put(DatiMetaData.DATI_USERNAME_KEY, username);
        cv.put(DatiMetaData.DATI_PASSWORD_KEY, password);
        cv.put(DatiMetaData.DATI_EMAIL_KEY, email);
        cv.put(DatiMetaData.DATI_CELLULARE_KEY, cellulare);
        cv.put(DatiMetaData.DATI_CODICE_KEY, codice);
        mDb.insert(DatiMetaData.DATI_TABLE, null, cv);

    }

    public Cursor fetchDati() //metodo che fa la query di tutti i dati
    {
        return mDb.query(DatiMetaData.DATI_TABLE, null,null,null, null, null, null);

    }

    static class DatiMetaData
    {
        static final String DATI_TABLE = "Dati";
        static final String ID = "_id";
        static final String DATI_UTENTE_KEY = "Utente";
        static final String DATI_USERNAME_KEY = "Username";
        static final String DATI_PASSWORD_KEY = "Password";
        static final String DATI_EMAIL_KEY = "Email";
        static final String DATI_CELLULARE_KEY = "Cellulare";
        static final String DATI_CODICE_KEY = "Codice";
    }

    private static final String DATI_TABLE_CREATE= "Crea la tabella se non esiste"
            + DatiMetaData.DATI_TABLE + " ("
            + DatiMetaData.ID + " chiave primaria intera autoincremento, "
            + DatiMetaData.DATI_UTENTE_KEY + " utente not null"
            + DatiMetaData.DATI_USERNAME_KEY + " username not null"
            + DatiMetaData.DATI_PASSWORD_KEY + " password not null"
            + DatiMetaData.DATI_EMAIL_KEY + " email not null"
            + DatiMetaData.DATI_CELLULARE_KEY + " cellulare not null"
            + DatiMetaData.DATI_CODICE_KEY + " codice not null";


    private class DbHelper extends SQLiteOpenHelper
    {
        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATI_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {

        }
    }


}
