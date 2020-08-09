package com.dagger.hilt.network

import com.dagger.hilt.model.Employee
import com.dagger.hilt.model.ServerResponse
import retrofit2.http.GET


interface ApiService {

    @GET("v1/employees")
    suspend fun getEmployeeList() : ServerResponse<List<Employee>>

}