package com.dagger.hilt.di

import com.dagger.hilt.data.repository.EmployeeRepository
import com.dagger.hilt.data.repository.EmployeeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class EmployeeModule {

    @Binds
    abstract fun provideChallengeRepository(
        employeeRepositoryImpl: EmployeeRepositoryImpl
    ) : EmployeeRepository

}