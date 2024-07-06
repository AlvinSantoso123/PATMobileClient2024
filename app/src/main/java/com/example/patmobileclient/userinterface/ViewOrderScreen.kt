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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.patmobileclient.network.Order
import com.example.patmobileclient.network.OrdersResponse
import com.example.patmobileclient.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ViewOrdersScreen(customerId: Int, navigateBack: () -> Unit) {
    val context = LocalContext.current
    val orders = remember { mutableStateOf(emptyList<Order>()) }
    Log.d("ViewOrderScreen", customerId.toString())

    LaunchedEffect(Unit) {
        RetrofitInstance.api.getOrders(customerId).enqueue(object : Callback<OrdersResponse> {
            override fun onResponse(call: Call<OrdersResponse>, response: Response<OrdersResponse>) {
                if (response.isSuccessful && response.body()?.status == 200) {
                    val checkResponse = response.body()
                    orders.value = response.body()?.response ?: emptyList()
                    Log.d("Response", checkResponse.toString())
                } else {
                    Toast.makeText(context, response.body()?.error ?: "Failed to fetch orders", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
                Toast.makeText(context, t.message ?: "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(64.dp))
            Text(text = "View Order")
            Button(onClick = navigateBack) {
            Text("Back to Menu")
        }
        orders.value.forEach { order ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Order ID: ${order.order_id}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Berat Barang: ${order.berat_barang}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Alamat Pengambilan: ${order.alamat_pengambilan}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Alamat Pengiriman: ${order.alamat_pengiriman}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Harga: ${order.harga}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Pickup Time: ${order.pickup}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Pickup Status: ${order.pickup_status}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Deliver Time: ${order.delivered}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Deliver Status: ${order.deliver_status}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Harga: ${order.harga}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Text("Harga: ${order.harga}")
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
    }

}