package com.chikadibia.teesas.network
import com.chikadibia.teesas.model.Preschool
import com.chikadibia.teesas.model.User
import retrofit2.Call
import retrofit2.http.*

interface BackEndApi {
    @FormUrlEncoded
    @POST("auth/login")
    fun LOGIN(@Field("phone") phone: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("auth/forgot")
    fun FORGOT(@Field("phone") phone: String): Call<User>


    @FormUrlEncoded
    @POST("/schools/type/get")
    fun GETSCHOOL(@Field("type") type: String): Call<List<Preschool?>>

    @Headers("Content-Type","application/json")
    @POST("/register")
    fun REGISTER(@Body data: String) : Call<User>

}