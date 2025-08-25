package com.app.thepunjabifeast.injection

import android.content.Context
import android.net.ConnectivityManager
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.network.ApiService
import com.app.thepunjabifeast.helpers.AndroidConnectivityObserver
import com.app.thepunjabifeast.helpers.ConnectivityObserver
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   /*
   multiple implementations
   @Provides
    @Singleton
    @Named("hello1")
    fun providesString1() = "Hello 1"

    @Provides
    @Singleton
    @Named("hello2")
    fun providesString2() = "Hello 2"

    //usage
     @Provides
    @Singleton
    fun provideCredentialManager(
     @ApplicationContext context: Context,
     @Named("hello1") hello1: String
    ):  CredentialManager = CredentialManager.create(context)
    */

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore = Firebase.firestore

  /*
    @Provides
    fun provideValidationHelper(impl: ValidationHelperImpl): ValidationHelper = impl*/

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideConnectivityHelper(
        @ApplicationContext context: Context,
        connectivityManager: ConnectivityManager
    ): ConnectivityObserver {
        return AndroidConnectivityObserver(context, connectivityManager)
    }


    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return Retrofit.Builder()
           // .baseUrl("https://6471a6946a9370d5a41a84bb.mockapi.io/")
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOAuthProvider(): OAuthProvider {
        return OAuthProvider.newBuilder("twitter.com")
            .addCustomParameter("lang", "en")
            .build()
    }

    @Provides
    @Singleton
    fun provideCredentialManager( @ApplicationContext context: Context):
            CredentialManager = CredentialManager.create(context)

    @Provides
    fun provideGoogleIdOption(
        @ApplicationContext context: Context
    ): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .build()
    }


    @Provides
    fun provideCredentialRequest(
        googleIdOption: GetGoogleIdOption
    ): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

}