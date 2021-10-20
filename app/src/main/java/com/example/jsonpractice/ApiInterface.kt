
package com.example.jsonpractice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/contacts/")
    fun getUser():Call<Array<DataItem>>


}