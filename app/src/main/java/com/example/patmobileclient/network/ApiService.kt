package com.example.patmobileclient.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("account")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("addorder/{id}")
    fun addOrder(@Path("id") id: Int, @Body request: OrderRequest): Call<OrderResponse>

    @GET("customergetorder/{customerid}")
    fun getOrders(@Path("customerid") customerid: Int): Call<OrdersResponse>
}
