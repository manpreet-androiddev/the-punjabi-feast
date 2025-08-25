package com.app.thepunjabifeast.data.repository

import android.app.Activity
import com.app.thepunjabifeast.data.model.SignInRequest
import com.app.thepunjabifeast.data.model.SignInResponse
import com.app.thepunjabifeast.network.ApiService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AuthRepository {
    suspend fun signInAnonymously(): FirebaseUser
    suspend fun login(email: String, password: String): SignInResponse
    suspend fun deleteAccount()
    suspend fun signUp(email: String, password: String): FirebaseUser
    suspend fun signIn(email: String, password: String): FirebaseUser
    suspend fun signInWithTwitter(activity: Activity): FirebaseUser
    suspend fun signInWithGoogle(idToken: String): FirebaseUser
    suspend fun signOut(): Boolean
}

class AuthRepositoryImpl @Inject constructor(
    private val defaultApiService: ApiService,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    val currentUser: FirebaseUser? = authRemoteDataSource.currentUser
    val currentUserIdFlow: Flow<String?> = authRemoteDataSource.currentUserIdFlow

    override suspend fun signUp(email: String, password: String): FirebaseUser {
        return authRemoteDataSource.createAccount(email, password)
    }

    override suspend fun signIn(email: String, password: String): FirebaseUser {
        return authRemoteDataSource.signIn(email, password)
    }

    override suspend fun signInWithTwitter(activity: Activity): FirebaseUser {
        return authRemoteDataSource.signInWithTwitter(activity)
    }

    override suspend fun signInWithGoogle(idToken: String): FirebaseUser {
        return authRemoteDataSource.firebaseAuthWithGoogle(idToken)
    }

    override suspend fun signOut(): Boolean {
        return authRemoteDataSource.signOut()
    }

    override suspend fun deleteAccount() {
        authRemoteDataSource.deleteAccount()
    }

    override suspend fun signInAnonymously(): FirebaseUser {
        return authRemoteDataSource.signInAnonymously()
    }

    // server login
    override suspend fun login(email: String, password: String): SignInResponse {
        return defaultApiService.signIn(SignInRequest(email, password))
    }
}