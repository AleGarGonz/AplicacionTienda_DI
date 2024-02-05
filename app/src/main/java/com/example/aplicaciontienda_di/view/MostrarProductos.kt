package com.example.aplicaciontienda_di.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aplicaciontienda_di.R
import com.example.aplicaciontienda_di.model.Producto
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarProductos(navController: NavController){
    Scaffold(topBar = { TopAppBar01(navController,"Productos") }) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds // Escala la imagen para llenar el Box
            )
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val valuePased = navBackStackEntry?.arguments?.getString("parametro")
            val listaProductos: List<Producto>
            runBlocking {
                listaProductos = getProductos(valuePased.toString())
                listaProductos.forEach { println(it) }
            }
            Log.e("f", "" + listaProductos)
            RecyclerProductos(listaProductos,navController)
            SplashScreen()
        }
    }
}
@Composable
fun SplashScreen() {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Cambia este valor según la duración deseada del splash screen
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(vertical = 100.dp).padding(horizontal = 60.dp), color = Color.Red, strokeWidth = 8.dp)
        }// Color del ProgressBar
    }
}