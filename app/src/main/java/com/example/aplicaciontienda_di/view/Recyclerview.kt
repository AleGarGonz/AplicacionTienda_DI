package com.example.aplicaciontienda_di.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicaciontienda_di.R
import com.example.aplicaciontienda_di.model.Producto
import com.example.aplicaciontienda_di.viewmodel.Routes

@Composable
fun RecyclerProductos(list: List<Producto>,navController: NavController){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp,vertical=8.dp)
    ){
        items(list){ objeto->
            Divider(color= Color.LightGray, thickness = 2.dp)
            CardProductos(objeto=objeto,navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardProductos(objeto: Producto,navController: NavController) {
    var show by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current//De momento no me hace falta lo utiliza para pasaselo a un toaast
    var imagenProducto:Int = 0;
    val imagenes =mapOf("A1" to R.drawable.manzanas,"A2" to R.drawable.ensalada,"A3" to R.drawable.kebab,"A4" to R.drawable.pollo,
                        "E1" to R.drawable.portatil,"E2" to R.drawable.movil,"E3" to R.drawable.cascos,"E4" to R.drawable.ipad,
                        "H1" to R.drawable.lampara,"H2" to R.drawable.sofa, "H3" to R.drawable.vater,"H4" to R.drawable.nevera,
                        "R1" to R.drawable.chaqueta,"R2" to R.drawable.camiseta,"R3" to R.drawable.gorro,"R4" to R.drawable.gafas)
    for ((clave, valor) in imagenes) {
        if (clave == objeto.id) {
            imagenProducto = valor
        }
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.Red),
        onClick = {
            show=true
        }
    ) {
        Box(modifier= Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.cardbackgroundrecy),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imagenProducto),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = objeto.nombre, fontSize = 23.sp)
                Spacer(modifier = Modifier.width(18.dp))
                Column(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalAlignment = Alignment.End
                ){
                    var isFavorited by remember { mutableStateOf(false) }
                    val starColor = if (isFavorited) Color.Yellow else Color.DarkGray
                    IconButton(onClick = { isFavorited = !isFavorited }) {
                        StarIcon(
                            color = starColor,
                            onClick = { isFavorited = !isFavorited }
                        )
                    }
                }
            }
        }
    }
    ProductAlertDialog(show,objeto.nombre,objeto.precio.toString(),painterResource(id = imagenProducto),onDismiss = { show = false },
        onConfirm = {  show = false;navController.navigate(route= Routes.Compra.route) })
}
@Composable
fun StarIcon(color: Color, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.StarHalf,
        contentDescription = null,
        modifier = Modifier
            .size(44.dp)
            .padding(3.dp),
        tint = color
    )
}
@Composable
fun ProductAlertDialog(
    show: Boolean,
    productName: String,
    productPrice: String,
    productImage: Painter,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            containerColor = Color.hsl(120f, 0.5f, 0.7f),
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Detalles del Producto") },
            text = {
                Column {
                    Text(text = "$productName",fontSize=24.sp,fontWeight = FontWeight.Bold)
                    Text(text = "Precio: $productPrice €",fontSize=24.sp,fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = productImage,
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .fillMaxSize()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Comprar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Atras")
                }
            }
        )
    }
}
