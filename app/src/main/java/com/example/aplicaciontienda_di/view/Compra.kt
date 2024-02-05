package com.example.aplicaciontienda_di.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicaciontienda_di.R
import com.example.aplicaciontienda_di.viewmodel.Routes
import kotlinx.coroutines.delay

@Composable
fun Compra(navController: NavController){
    BoxGraciasVolver(navController)
    SplashScreenCompra()
}
@Composable
fun SplashScreenCompra() {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Cambia este valor según la duración deseada del splash screen
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp),
            contentAlignment= Alignment.Center
        ) {
            Text(
                text = "¡Procesando el pago!",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            )
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp)
                .padding(horizontal = 60.dp), color = Color.Blue, strokeWidth = 8.dp)
        }// Color del ProgressBar
    }
}
@Composable
fun BoxGraciasVolver(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter =  painterResource(id = R.drawable.logotienda),
                modifier = Modifier
                    .height(140.dp)
                    .width(140.dp),
                contentDescription = "Descripicion de la imagen"
            )
            Spacer(modifier= Modifier.height(15.dp))
            Text(
                text = "¡Gracias por su compra!",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(Color.hsl(0f,0.5f,0.6f)),
                contentAlignment = Alignment.Center
                ){
                Button(onClick = { navController.navigate(route= Routes.Login.route) },colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                    Text(text = "Volver",color = Color.Red)
                }
            }
        }
    }
}