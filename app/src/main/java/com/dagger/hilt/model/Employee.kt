package com.dagger.hilt.model

import java.io.Serializable

class Employee : Serializable {

    var id: Long? = null
    var employee_name: String? = null
    var employee_salary: Int = 0
    var employee_age: Int = 0

}