package com.example.patmobileclient.network

data class LoginRequest(
    val username: String,
    val pass: String)

data class LoginResponse(
    val status: Int,
    val error: String?,
    val response: String,
    val type: String?,
    val id: Int?)

data class RegisterRequest(
    val username: String,
    val pass: String)

data class RegisterResponse(
    val status: Int,
    val error: String?,
    val response: Any)

data class OrderRequest(
    val berat_barang: Int,
    val alamat_pengambilan: String,
    val alamat_pengiriman: String,
    val harga: Double)

data class OrderResponse(
    val status: Int,
    val error: String?,
    val response: Any)

data class OrdersResponse(
    val status: Int,
    val error: String?,
    val response: List<Order>)

data class Order(
    val order_id: Int,
    val berat_barang: Int,
    val alamat_pengambilan: String,
    val alamat_pengiriman: String,
    val harga: Double,
    val pickup: String,
    val pickup_status: Int,
    val delivered: String,
    val deliver_status: Int,
    val customerid: Int)
