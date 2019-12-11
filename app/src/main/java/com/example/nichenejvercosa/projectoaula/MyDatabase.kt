package com.example.nichenejvercosa.projectoaula

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabase(context: Context): SQLiteOpenHelper(context, "aula.db", null, 1) {



    private val TAG = "MyDatabase"

    private val TABLE_USER = "User"
    private val ID_USER: String = "ID"
    private val NOME_USER: String = "NOME"
    private val SENHA_USER: String = "SENHA"

    private val TABLE_CARD = "Card"
    private val ID_CARD: String = "ID"
    private val TITULO_CARD: String = "NOME"
    private val SUBTITULO_CARD: String = "SENHA"
    private val STATUS_CARD : String = "STATUS"
    private val CARD_USER : String = "USER"

    private val DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_USER + " (" +
                    "${ID_USER} integer PRIMARY KEY autoincrement," +
                    "${NOME_USER} text," +
                    "${SENHA_USER} text" +
                    ");"

    private val DATABASE_CARD =
            "CREATE TABLE if not exists " + TABLE_CARD + " (" +
                    "${ID_CARD} integer PRIMARY KEY autoincrement," +
                    "${TITULO_CARD} text," +
                    "${SUBTITULO_CARD} text, " +
                    "${STATUS_CARD} text, " +
                    "${CARD_USER} integer," +
                    " FOREIGN KEY ("+CARD_USER+") REFERENCES "+TABLE_USER+"("+ID_USER+")" +
                    ");"

    fun insertUser(nome: String, senha: String) {
        val values = ContentValues()
        values.put(NOME_USER, nome)
        values.put(SENHA_USER, senha)
        writableDatabase.insert(TABLE_USER, null, values);
        writableDatabase.close()
    }

    fun getUser(name : String): User? {
        var cursor =  readableDatabase
                .query(TABLE_USER, arrayOf(ID_USER, NOME_USER, SENHA_USER), "${NOME_USER} = \"${name}\" ", null, null, null, null);

        var user = User(0, "", "")

        if(cursor.moveToFirst()){
            user.id = cursor.getInt(cursor.getColumnIndex(ID_USER))
            user.name = cursor.getString(cursor.getColumnIndex(NOME_USER))
            user.password =  cursor.getString(cursor.getColumnIndex(SENHA_USER))

        }
        cursor.close()
        close()
        return user
    }

    /*
    fun getLog(id: Int) : Cursor {
        return getReadableDatabase()
                .query(TABLE, arrayOf(ID, TIMESTAMP, TEXT), "${ID}=${id}", null, null, null, null)
    }

    fun updateLog(id: Int, text: String) {
        val values = ContentValues()
        values.put(TEXT, text)
        values.put(TIMESTAMP, System.currentTimeMillis())

        getWritableDatabase().update(TABLE, values, "${ID}=${id}", null)
    }


    fun removeLog(id: Int): Int {
        return writableDatabase.delete(TABLE, "${ID}=${id}", null)
    }
    */

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("MyDatabase", "Creating: $DATABASE_CREATE")
        db?.execSQL(DATABASE_CREATE)
        db?.execSQL(DATABASE_CARD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("PRAGMA foreign_keys=ON")
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_USER)
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_CARD)
        onCreate(db);
    }


}