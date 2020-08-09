package com.dagger.hilt.data.repository

import com.dagger.hilt.model.Employee
import com.dagger.hilt.model.ServerResponse

abstract class EmployeeRepository : Repository() {

    abstract suspend fun getEmployeeList() : ServerResponse<List<Employee>>

}