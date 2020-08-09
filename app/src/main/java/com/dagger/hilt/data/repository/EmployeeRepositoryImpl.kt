package com.dagger.hilt.data.repository

import com.dagger.hilt.model.Employee
import com.dagger.hilt.model.ServerResponse
import com.dagger.hilt.network.ApiService
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : EmployeeRepository() {

    //**********************************************************************************************
    override suspend fun getEmployeeList(): ServerResponse<List<Employee>> {
        return apiService.getEmployeeList()
    }

}