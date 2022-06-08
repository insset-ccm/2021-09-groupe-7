package com.capou.application.api

import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface Details {
 /*   @GET("{num}")
   fun getDetailsProduct(@Path("num") num : String):Call<DetailsRoom>
   */

  /* @GET("{name}")
   fun getDetails(@Path("name") num : String):Call<DetailsProduct>*/

   @GET("getAlimentByName")
   fun getDetails(@Query("name") name : String):Call<DetailsProduct>



}