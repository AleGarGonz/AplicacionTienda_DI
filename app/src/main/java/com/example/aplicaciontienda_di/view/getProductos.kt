package com.example.aplicaciontienda_di.view

import android.util.Log
import com.example.aplicaciontienda_di.model.Producto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

val db = FirebaseFirestore.getInstance()
suspend fun getProductos(tabla:String): List<Producto> {
    val productos = mutableListOf<Producto>()

    try {
        val querySnapshot = db.collection(tabla).get().await()

        for (document in querySnapshot.documents) {
            val nombre = document.getString("nombre") ?: ""
            val precio = document.getDouble("precio") ?: 0.0
            val id = document.getString("id") ?: ""
            productos.add(Producto(id, nombre, precio))
        }
    } catch (e: Exception) {
        Log.e("TAG", "Error al obtener los productos", e)
    }

    return productos
}