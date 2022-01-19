package ir.arinateam.shopadmin.api

import ir.arinateam.shopadmin.shop.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
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


    @GET("orderList/")
    fun orderList(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int
    ): Call<ModelRecOrderBase>


    @GET("orderDetail/")
    fun orderDetail(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Query("orderId") orderId: Int,
    ): Call<ModelRecOrderDetailBase>


    @GET("productList/")
    fun productList(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int
    ): Call<ModelRecProductBase>


    @GET("removeProduct/")
    fun removeProduct(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Query("productId") productId: Int,
    ): Call<ResponseBody>


    @GET("categoryList/")
    fun categoryList(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int
    ): Call<ModelSpCategoryBase>


    @GET("editProduct/")
    fun editProductWithImage(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Part image: MultipartBody.Part,
        @Query("previewsImageId") previewsImageId: Int,
        @Query("productId") productId: Int,
        @Query("productName") productName: String,
        @Query("productWriter") productWriter: String,
        @Query("productPublisher") productPublisher: String,
        @Query("productCategory") productCategory: Int,
        @Query("productPrice") productPrice: String,
        @Query("productPageCount") productPageCount: Int,
        @Query("productPublishYear") productPublishYear: Int,
        @Query("productISBN") productISBN: String,
        @Query("productAvailableCount") productAvailableCount: Int,
        @Query("productDiscount") productDiscount: Int,
        @Query("productDescription") productDescription: String
    ): Call<ResponseBody>

    @GET("editProduct/")
    fun editProductWithoutImage(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Query("productId") productId: Int,
        @Query("productName") productName: String,
        @Query("productWriter") productWriter: String,
        @Query("productPublisher") productPublisher: String,
        @Query("productCategory") productCategory: Int,
        @Query("productPrice") productPrice: String,
        @Query("productPageCount") productPageCount: Int,
        @Query("productPublishYear") productPublishYear: Int,
        @Query("productISBN") productISBN: String,
        @Query("productAvailableCount") productAvailableCount: Int,
        @Query("productDiscount") productDiscount: Int,
        @Query("productDescription") productDescription: String
    ): Call<ResponseBody>

    @GET("addProduct/")
    fun addProduct(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Part image: MultipartBody.Part,
        @Query("productName") productName: String,
        @Query("productWriter") productWriter: String,
        @Query("productPublisher") productPublisher: String,
        @Query("productCategory") productCategory: Int,
        @Query("productPrice") productPrice: String,
        @Query("productPageCount") productPageCount: Int,
        @Query("productPublishYear") productPublishYear: Int,
        @Query("productISBN") productISBN: String,
        @Query("productAvailableCount") productAvailableCount: Int,
        @Query("productDiscount") productDiscount: Int,
        @Query("productDescription") productDescription: String
    ): Call<ResponseBody>

}