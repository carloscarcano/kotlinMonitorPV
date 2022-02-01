package com.cc.monitor

import com.cc.monitor.models.LoginRequestModel
import com.cc.monitor.models.LoginResultModel
import com.cc.monitor.models.SensorModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestServicios {
    @Headers("Content-Type: application/json")
    @POST("validarLogin")
    suspend fun validarLogin(@Body datoslogin: LoginRequestModel): Response<LoginResultModel>

    @Headers("Content-Type: application/json")
    @GET("sensores")
    suspend fun obtenerSensores(): Response<List<SensorModel>>
}
