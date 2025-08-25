package com.app.thepunjabifeast.injection

import com.app.thepunjabifeast.data.repository.AuthRepository
import com.app.thepunjabifeast.data.repository.AuthRepositoryImpl
import com.app.thepunjabifeast.helpers.ValidationHelper
import com.app.thepunjabifeast.helpers.ValidationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun provideValidationHelper(
        validationHelperImpl: ValidationHelperImpl
    ): ValidationHelper

}