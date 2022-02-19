package ir.arinateam.shopadmin.api

import ir.arinateam.shopadmin.admin.model.ModelAdminDashboardInfo
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase2
import ir.arinateam.shopadmin.admin.model.ModelGetAdminSell
import ir.arinateam.shopadmin.login.model.ModelLogin
import ir.arinateam.shopadmin.login.model.ModelSignup
import ir.arinateam.shopadmin.shop.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Headers("Accept: application/json")
    @POST("users/login")
    fun login(
        @Query("phone") phoneNumber: String,
        @Query("password") password: String,
        @Query("model") model: String
    ): Call<ModelLogin>

    @Headers("Accept: application/json")
    @POST("users/logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @Headers("Accept: application/json")
    @POST("users/register")
    fun signup(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("type") type: String,
        @Query("model") model: String
    ): Call<ModelSignup>

    @Headers("Accept: application/json")
    @GET("orders")
    fun orderList(
        @Header("Authorization") token: String,
        @Header("withDetails") withDetails: Boolean = true,
        @Header("withProduct") withProduct: Boolean = true
    ): Call<ModelGetOrdersBase>

    @Headers("Accept: application/json")
    @GET("orders/{id}")
    fun orderDetail(
        @Header("Authorization") token: String,
        @Path("id") orderId: Int,
        @Query("withOrder") withOrder: Boolean = true,
        @Query("withProduct") withProduct: Boolean = true
    ): Call<ModelRecOrderDetailBase>


    @Headers("Accept: application/json")
    @GET("products")
    fun productList(
        @Header("Authorization") token: String
    ): Call<ModelRecProductBase>

    @Headers("Accept: application/json")
    @GET("products/{id}")
    fun productInfo(
        @Header("Authorization") token: String,
        @Path("id") productId: Int,
        @Query("wantShopDetail") wantShopDetail: Boolean = false,
        @Query("wantCategoryDetail") wantCategoryDetail: Boolean = true,
        @Query("wantUserDetail") wantUserDetail: Boolean = false,
        @Query("wantComments") wantComments: Boolean = false,
        @Query("wantCommentUser") wantCommentUser: Boolean = false,
        @Query("sameWriterProducts") sameWriterProducts: Boolean = false
    ): Call<ModelRecProductInfoBase>


    @Headers("Accept: application/json")
    @DELETE("products/{id}")
    fun removeProduct(
        @Header("Authorization") token: String,
        @Path("id") productId: Int,
    ): Call<ResponseBody>


    @Headers("Accept: application/json")
    @GET("categories")
    fun categoryList(
        @Header("Authorization") token: String
    ): Call<ModelSpCategoryBase>

    @Multipart
    @Headers("Accept: application/json")
    @POST("products/{id}")
    fun editProductWithImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Path("id") productId: Int,
        @Query("name") productName: String,
        @Query("writer") productWriter: String,
        @Query("publisher") productPublisher: String,
        @Query("category") productCategory: Int,
        @Query("price") productPrice: String,
        @Query("pages") productPageCount: Int,
        @Query("publishYear") productPublishYear: Int,
        @Query("isbn") productISBN: String,
        @Query("amount") productAvailableCount: Int,
        @Query("discount") productDiscount: Int,
        @Query("description") productDescription: String
    ): Call<ResponseBody>

    @Headers("Accept: application/json")
    @POST("products/{id}")
    fun editProductWithoutImage(
        @Header("Authorization") token: String,
        @Path("id") productId: Int,
        @Query("name") productName: String,
        @Query("writer") productWriter: String,
        @Query("publisher") productPublisher: String,
        @Query("category") productCategory: Int,
        @Query("price") productPrice: String,
        @Query("pages") productPageCount: Int,
        @Query("publishYear") productPublishYear: Int,
        @Query("isbn") productISBN: String,
        @Query("amount") productAvailableCount: Int,
        @Query("discount") productDiscount: Int,
        @Query("description") productDescription: String
    ): Call<ResponseBody>


    @Multipart
    @Headers("Accept: application/json")
    @POST("products")
    fun addProduct(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Query("name") productName: String,
        @Query("writer") productWriter: String,
        @Query("publisher") productPublisher: String,
        @Query("category") productCategory: Int,
        @Query("price") productPrice: String,
        @Query("pages") productPageCount: Int,
        @Query("publishYear") productPublishYear: Int,
        @Query("isbn") productISBN: String,
        @Query("amount") productAvailableCount: Int,
        @Query("discount") productDiscount: Int,
        @Query("description") productDescription: String
    ): Call<ResponseBody>

    @Headers("Accept: application/json")
    @GET("dashboard")
    fun shopDashboard(
        @Header("Authorization") token: String
    ): Call<ModelGetShopDashboard>


    @Headers("Accept: application/json")
    @GET("users")
    fun shopInfo(
        @Header("Authorization") token: String,
        @Query("withShop") withShop: Boolean = true
    ): Call<ModelGetShopInfoBase>


    @Headers("Accept: application/json")
    @Multipart
    @POST("users")
    fun editShopInfoWithImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Query("shopName") shopName: String,
        @Query("name") username: String,
        @Query("phone") phoneNumber: String,
        @Query("shopAddress") shopAddress: String
    ): Call<ResponseBody>


    @Headers("Accept: application/json")
    @POST("users")
    fun editShopInfoWithoutImage(
        @Header("Authorization") token: String,
        @Query("shopName") shopName: String,
        @Query("name") username: String,
        @Query("phone") phoneNumber: String,
        @Query("shopAddress") shopAddress: String
    ): Call<ResponseBody>


    @Headers("Accept: application/json")
    @GET("shops")
    fun getShopsList(
        @Header("Authorization") token: String,
        @Query("withProducts") withProducts: Boolean = false,
        @Query("withUser") withUser: Boolean = false,
    ): Call<ModelAdminShopsInfoBase2>


    @Headers("Accept: application/json")
    @PUT("shops/{id}")
    fun changeShopState(
        @Header("Authorization") token: String,
        @Path("id") shopId: Int
    ): Call<ResponseBody>


    @Headers("Accept: application/json")
    @GET("sales")
    fun adminSell(
        @Header("Authorization") token: String
    ): Call<ModelGetAdminSell>

}