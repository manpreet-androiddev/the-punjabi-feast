package com.app.thepunjabifeast.data.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject
import com.google.firebase.auth.OAuthProvider
import timber.log.Timber

class AuthRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    private val oAuthProvider: OAuthProvider,
) {

    val currentUser: FirebaseUser? get() = auth.currentUser

    val currentUserIdFlow: Flow<String?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { _ -> this.trySend(currentUser?.uid) }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    suspend fun signInAnonymously(): FirebaseUser {
        val authResult = auth.signInAnonymously().await()
        return authResult.user
            ?: throw IllegalStateException("Anonymous sign-in succeeded but user is null")
    }

    suspend fun signIn(email: String, password: String): FirebaseUser {
        val authResult = auth.signInWithEmailAndPassword(email, password).await()
        return authResult.user
            ?: throw IllegalStateException("Twitter sign-in succeeded but user is null")
    }

    suspend fun createAccount(email: String, password: String): FirebaseUser {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()

        return authResult.user
            ?: throw IllegalStateException("Twitter sign-in succeeded but user is null")
    }

    suspend fun firebaseAuthWithGoogle(idToken: String): FirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val authResult = auth.signInWithCredential(credential).await()
        return authResult.user
            ?: throw IllegalStateException("Twitter sign-in succeeded but user is null")
    }

    suspend fun signInWithTwitter(activity: Activity): FirebaseUser {
        val result = if (auth.pendingAuthResult != null) {
            // Already a pending sign-in flow
            auth.pendingAuthResult!!.await()
        } else {
            // Start a new sign-in flow
            auth.startActivityForSignInWithProvider(activity, oAuthProvider).await()
        }

        Timber.i("Twitter sign-in success: %s", result.user?.uid)

        return result.user
            ?: throw IllegalStateException("Twitter sign-in succeeded but user is null")
    }

    suspend fun signOut(): Boolean {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())

        auth.currentUser?.let { user ->
            if (user.isAnonymous) {
                user.delete().await()
            }
        }

        auth.signOut()

        return true
    }

    suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }
}