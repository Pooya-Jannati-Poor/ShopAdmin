package ir.arinateam.shopadmin.api

import ir.arinateam.shopadmin.admin.model.ModelAdminDashboardInfo
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase
import ir.arinateam.shopadmin.admin.model.ModelGetAdminSell
import ir.arinateam.shopadmin.login.model.ModelSignup
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


    @GET("checkLogin/")
    fun checkLogin(
        @Header("Authorization") token: String
    ): Call<ResponseBody>


    @GET("login/")
    fun login(
        @Header("Authorization") token: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("password") password: String
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("user/register")
    fun signup(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("type") type: String,
        @Query("model") model: String
    ): Call<ModelSignup>


    @GET("orderList/")
    fun orderList(
        @Header("Authorization") token: String,
    ): Call<ModelRecOrderBase>


    @GET("orderDetail/")
    fun orderDetail(
        @Header("Authorization") token: String,
        @Query("orderId") orderId: Int,
    ): Call<ModelRecOrderDetailBase>


    @GET("products/")
    fun productList(
        @Header("Authorization") token: String
    ): Call<ModelRecProductBase>


    @GET("products/")
    fun productInfo(
        @Header("Authorization") token: String,
        @Query("id") productId: Int
    ): Call<ModelRecProductInfo>


    @DELETE("products/{id}")
    fun removeProduct(
        @Header("Authorization") token: String,
        @Path("id") productId: Int,
    ): Call<ResponseBody>


    @GET("category/")
    fun categoryList(
        @Header("Authorization") token: String
    ): Call<ModelSpCategoryBase>


    @FormUrlEncoded
    @POST("products/")
    fun editProductWithImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Field("id") productId: Int,
        @Field("name") productName: String,
        @Field("writer") productWriter: String,
        @Field("publisher") productPublisher: String,
        @Field("category") productCategory: Int,
        @Field("price") productPrice: String,
        @Field("pages") productPageCount: Int,
        @Field("publishYear") productPublishYear: Int,
        @Field("isbn") productISBN: String,
        @Field("amount") productAvailableCount: Int,
        @Field("discount") productDiscount: Int,
        @Field("description") productDescription: String
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("products/")
    fun editProductWithoutImage(
        @Header("Authorization") token: String,
        @Field("id") productId: Int,
        @Field("name") productName: String,
        @Field("writer") productWriter: String,
        @Field("publisher") productPublisher: String,
        @Field("category") productCategory: Int,
        @Field("price") productPrice: String,
        @Field("pages") productPageCount: Int,
        @Field("publishYear") productPublishYear: Int,
        @Field("isbn") productISBN: String,
        @Field("amount") productAvailableCount: Int,
        @Field("discount") productDiscount: Int,
        @Field("description") productDescription: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("products/")
    fun addProduct(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Field("name ") productName: String,
        @Field("writer ") productWriter: String,
        @Field("publisher ") productPublisher: String,
        @Field("category ") productCategory: Int,
        @Field("price ") productPrice: String,
        @Field("pages") productPageCount: Int,
        @Field("publishYear") productPublishYear: Int,
        @Field("isbn ") productISBN: String,
        @Field("amount ") productAvailableCount: Int,
        @Field("discount") productDiscount: Int,
        @Field("description ") productDescription: String
    ): Call<ResponseBody>


    @GET("shopDashboard/")
    fun shopDashboard(
        @Header("Authorization") token: String
    ): Call<ModelGetShopDashboard>


    @GET("shopInfo/")
    fun shopInfo(
        @Header("Authorization") token: String
    ): Call<ModelGetShopInfo>


    @GET("editShopInfo/")
    fun editShopInfoWithImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Query("shopName") shopName: String,
        @Query("username") username: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("shopAddress") shopAddress: String
    ): Call<ResponseBody>


    @GET("editShopInfo/")
    fun editShopInfoWithoutImage(
        @Header("Authorization") token: String,
        @Query("shopName") shopName: String,
        @Query("username") username: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("shopAddress") shopAddress: String
    ): Call<ResponseBody>


    @GET("getShopsList/")
    fun getShopsList(
        @Header("Authorization") token: String
    ): Call<ModelAdminShopsInfoBase>


    @GET("adminDashboardInfo/")
    fun adminDashboardInfo(
        @Header("Authorization") token: String
    ): Call<ModelAdminDashboardInfo>


    @GET("changeShopState/")
    fun changeShopState(
        @Header("Authorization") token: String,
        @Query("shopId") shopId: Int
    ): Call<ResponseBody>


    @GET("adminSell/")
    fun adminSell(
        @Header("Authorization") token: String
    ): Call<ModelGetAdminSell>

}