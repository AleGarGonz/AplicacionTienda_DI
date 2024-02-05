package com.example.aplicaciontienda_di.viewmodel

import com.example.aplicaciontienda_di.model.Producto

sealed class Routes(val route:String){
    object Login : Routes("login_screen")
    object Categorias : Routes("categorias_screen")
    object Mostrar : Routes("mostrar_screen/{parametro}") {
        fun createRoute(parametro: String) = "mostrar_screen/$parametro"
    }
    object Compra : Routes("compra_screen")
}
