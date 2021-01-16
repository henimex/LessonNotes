package com.hendev.notes

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotlarDaoInterface {

    @GET("services/tum_notlar.php")
    fun tumNotlar() : Call<NotlarCevap>

    @POST("services/delete_not.php")
    @FormUrlEncoded
    fun notSil(@Field("not_id") not_id:Int) : Call<CRUDCevap>

    @POST("services/insert_not.php")
    @FormUrlEncoded
    fun notEkle(@Field("not_id") not_id:Int) : Call<CRUDCevap>
}