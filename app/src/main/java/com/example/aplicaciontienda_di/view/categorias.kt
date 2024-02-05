package com.example.aplicaciontienda_di.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicaciontienda_di.R
import com.example.aplicaciontienda_di.viewmodel.Routes


@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Categorias(navController: NavController) {
        Scaffold(topBar = { TopAppBar01(navController,"Categorias") }) { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .background(
                        Color.White
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(), // Ocupa todo el espacio del Box
                    contentScale = ContentScale.FillBounds // Escala la imagen para llenar el Box
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    CardWithImageAndText(imageResId = R.drawable.alimentacion, text = "Alimentacion") {
                        navController.navigate(route= Routes.Mostrar.createRoute("alimentacion"))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    CardWithImageAndText(imageResId = R.drawable.electrico, text = "Electronica") {
                        navController.navigate(route= Routes.Mostrar.createRoute("electronica"))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    CardWithImageAndText(imageResId = R.drawable.hogar, text = "Hogar") {
                        navController.navigate(route= Routes.Mostrar.createRoute("hogar"))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    CardWithImageAndText(imageResId = R.drawable.ropa, text = "Ropa y Accesorios") {
                        navController.navigate(route= Routes.Mostrar.createRoute("ropa"))
                    }
                }
            }
        }

    }

    @Composable
    fun CardWithImageAndText(imageResId: Int, text: String,onClick: () -> Unit) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier= Modifier.fillMaxWidth().padding(6.dp).size(100.dp)){
            Image(
                painter = painterResource(id = R.drawable.cardbackground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = text, fontSize = 23.sp,color=Color.White)
            }
        }
    }
    }