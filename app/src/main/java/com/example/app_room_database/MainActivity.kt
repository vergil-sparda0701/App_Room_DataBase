package com.example.app_room_database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var txtCodigo: EditText? = null
    var txtDescripcion: EditText? = null
    var txtPrecio: EditText? = null
    lateinit var con: SQLite
    lateinit var baseDatos: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCodigo = findViewById(R.id.txtCodigo)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPrecio = findViewById(R.id.txtPrecio)
        con = SQLite(this, "Tienda", null, 1)
        baseDatos = con.writableDatabase
    }

    fun insertar(view: View) {
        val codigo = txtCodigo?.text.toString()
        val descripcion = txtDescripcion?.text.toString()
        val precio = txtPrecio?.text.toString()

        if (codigo.isNotEmpty() && descripcion.isNotEmpty() && precio.isNotEmpty()) {
            val registro = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)
            baseDatos.insert("productos", null, registro)
            txtCodigo?.setText("")
            txtDescripcion?.setText("")
            txtPrecio?.setText("")
            Toast.makeText(this, "Se insertado exitosamente", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Los campos deben tener texto", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        baseDatos.close()
        con.close()
    }
}