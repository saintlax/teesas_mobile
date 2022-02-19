package com.chikadibia.teesas.network
import com.chikadibia.teesas.model.Preschool
import com.chikadibia.teesas.model.User
import retrofit2.Call
import retrofit2.http.*

interface BackEndApi {
    @FormUrlEncoded
    @POST("users/login")
    fun LOGIN(@Field("phone") phone: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("users/forgot")
    fun FORGOT(@Field("phone") phone: String): Call<User>


    @FormUrlEncoded
    @POST("/schools/type")
    fun GETSCHOOL(@Field("type") type: String): Call<List<Preschool?>>

    @FormUrlEncoded
    @POST("/register")
    fun REGISTER(@Field("body") body: String) : Call<User>

}