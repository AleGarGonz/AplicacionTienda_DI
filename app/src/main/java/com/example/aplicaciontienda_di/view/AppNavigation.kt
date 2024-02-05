package com.example.aplicaciontienda_di.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciontienda_di.viewmodel.LoginScreenViewModel
import com.example.aplicaciontienda_di.viewmodel.Routes

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route ){
        composable(route = Routes.Login.route){
            loginScreen(navController,LoginScreenViewModel())
        }
        composable(route = Routes.Categorias.route){
            Categorias(navController)
        }
        composable(route = Routes.Mostrar.route){
            MostrarProductos(navController)
        }
        composable(route = Routes.Compra.route){
            Compra(navController)
        }
    }
}
