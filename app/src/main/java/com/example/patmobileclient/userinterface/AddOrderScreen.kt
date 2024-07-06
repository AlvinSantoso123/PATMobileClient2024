package com.example.patmobileclient.userinterface

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.House
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Scale
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.patmobileclient.network.OrderRequest
import com.example.patmobileclient.network.OrderResponse
import com.example.patmobileclient.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AddOrderScreen(customerId: Int, navigateBack: () -> Unit) {
    val context = LocalContext.current
    val beratBarang = remember { mutableStateOf("") }
    val alamatPengambilan = remember { mutableStateOf("") }
    val alamatPengiriman = remember { mutableStateOf("") }
    val harga = remember { mutableStateOf("") }

    Log.d("AddOrderScreen", customerId.toString())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Add Order")
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
            modifier = Modifier.padding(32.dp),
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ){
                Icon(
                    Icons.Rounded.Scale,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = beratBarang.value,
                    onValueChange = { beratBarang.value = it },
                    label = {
                        Text("Berat Barang")
                    }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ){
                Icon(
                    Icons.Rounded.House,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(value = alamatPengambilan.value,
                    onValueChange = { alamatPengambilan.value = it },
                    label = {
                        Text("Alamat Pengambilan")
                    }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ){
                Icon(
                    Icons.Rounded.House,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(value = alamatPengiriman.value,
                    onValueChange = { alamatPengiriman.value = it },
                    label = {
                        Text("Alamat Pengiriman")
                    }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ){
                Icon(
                    Icons.Rounded.AttachMoney,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = harga.value,
                    onValueChange = { harga.value = it },
                    label = {
                        Text("Harga")
                    }
                )
            }
        }

        Button(onClick = {
            val orderRequest = OrderRequest(
                beratBarang.value.toInt(),
                alamatPengambilan.value,
                alamatPengiriman.value,
                harga.value.toDouble()
            )

            RetrofitInstance.api.addOrder(customerId, orderRequest).enqueue(object :
                Callback<OrderResponse> {
                override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        navigateBack()
                    } else {
                        Toast.makeText(context, response.body()?.error ?: "Order failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Toast.makeText(context, t.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text("Add Order")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateBack) {
            Text("Back to Menu")
        }
    }
}