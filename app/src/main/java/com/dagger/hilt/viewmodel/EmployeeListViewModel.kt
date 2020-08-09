package com.dagger.hilt.viewmodel

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dagger.hilt.data.repository.EmployeeRepository
import com.dagger.hilt.model.Employee
import com.dagger.hilt.viewmodel.base.DataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeListViewModel @ViewModelInject constructor(
    application: Application,
    private val employeeRepository: EmployeeRepository
) : AndroidViewModel(application) {

    private var mEmployeeListLiveDate: MutableLiveData<DataWrapper<List<Employee>>>? = null

    //**********************************************************************************************
    fun getEmployeeList() : LiveData<DataWrapper<List<Employee>>> {

        if (mEmployeeListLiveDate == null) {
            mEmployeeListLiveDate = MutableLiveData()
            mEmployeeListLiveDate!!.value = DataWrapper.loading()

            viewModelScope.launch {

                try {

                    val serverResponse = withContext(Dispatchers.IO) {
                        employeeRepository.getEmployeeList()
                    }

                    if (serverResponse.isSuccess()) {
                        mEmployeeListLiveDate!!.value = DataWrapper.success(
                            serverResponse.data
                        )
                    }
                    else {
                        mEmployeeListLiveDate!!.value = DataWrapper.error(
                            Throwable(serverResponse.message), serverResponse.message
                        )
                    }

                }
                catch (t: Throwable) {
                    Log.e("sepideh", "getEmployeeList: ", t)
                    mEmployeeListLiveDate!!.value = DataWrapper.error(t)
                }

            }

        }

        return mEmployeeListLiveDate!!
    }

}