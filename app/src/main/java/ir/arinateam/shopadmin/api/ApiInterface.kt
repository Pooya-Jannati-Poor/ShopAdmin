package ir.arinateam.shopadmin.api

import ir.arinateam.shopadmin.shop.model.ModelRecOrderBase
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetailBase
import ir.arinateam.shopadmin.shop.model.ModelRecProductBase
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    /*@FormUrlEncoded
    @POST("initial/data")
    fun loadingWithUUID(
        @Field("modelName") modelName: String,
        @Field("uuid") uuid: String,
    ): Call<ModelLoading>


    @GET("coffeeShops")
    fun cafeList(
        @Header("Authorization") token: String
    ): Call<ModelCafeList>*/


    @GET("orderList")
    fun orderList(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Call<ModelRecOrderBase>

    @GET("orderDetail")
    fun orderDetail(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
        @Path("orderId") orderId: Int,
    ): Call<ModelRecOrderDetailBase>

    @GET("productList")
    fun productList(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Call<ModelRecProductBase>


}