package com.example.yoneti.activities.signup

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.BuildConfig
import com.example.yonetimerchant.utils.MyPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel @ViewModelInject constructor(
    val repository: Repository,
    val preferences: MyPreferences
) : ViewModel() {

    var message: String? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    var emailObserver = MutableLiveData<Boolean>()
    var passwordObserver = MutableLiveData<Boolean>()
    var phoneObserver = MutableLiveData<Boolean>()
    var verifyUser = MutableLiveData<Boolean>()
    var email: String? = null
    var username: String? = null
    var password: String? = null
    var phone: String? = null
    var goingToLogin = MutableLiveData<Boolean>()
    var isSignUpSuccessful = MutableLiveData<Boolean>()
    var isRequesting = MutableLiveData<Boolean>()
    fun onSignUp() {
//        isSignUpSuccessful.value = true
        isRequesting.value = true
        if (password?.isNotEmpty() ?: false && email?.isNotEmpty() ?: false && phone?.isNotEmpty() ?: false) {
            viewModelScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.Main)
                {
                    var userCreated = repository.signup(
                        email!!, password!!, phone!!, "marchant", preferences.getDeviceToken()!!,
                        BuildConfig.DEVICE_TYPE, 1
                    )
                    if (userCreated.status) {
                        preferences.setUser(Gson().toJson((userCreated.result)))
                        preferences.setUserId(userCreated.result.userId)
                        message = userCreated.message
                        isRequesting.value = false
                        verifyUser.value = true
                    }
                    else
                    {
                        message = userCreated.message
                        isRequesting.value = false
                    }
                }
//
            }
        } else {
            viewModelScope.launch(Dispatchers.Main)
            {
                /**
                 * hide the progress bar
                 */
                isRequesting.value = false

                if (email?.isNotEmpty() ?: true)
                    emailObserver.value = true
                if (email?.isNotEmpty() ?: true)
                    passwordObserver.value = true
                if (email?.isNotEmpty() ?: true)
                    phoneObserver.value = true
                /**
                 * report empty fields inside the xml
                 */
            }
        }
    }


    fun getGoogleSigninClient(signupActivity: SignupActivity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.

        mGoogleSignInClient = GoogleSignIn.getClient(signupActivity, gso)
    }

    fun ifUserExistsOrNot(signupActivity: SignupActivity) {
        val account = GoogleSignIn.getLastSignedInAccount(signupActivity)
        if (account != null) {
            /***
             * store inside the shared preferences
             */
//            preferences.setGoogleAccountInfo()
            preferences.setLogin(true)
            signupActivity.updateUI(account)
        }
    }

    fun handleSignInResult(
        completedTask: Task<GoogleSignInAccount>?,
        signupActivity: SignupActivity
    ) {
        try {
            val account = completedTask?.getResult(ApiException::class.java)!!
            // Signed in successfully, show authenticated UI.
            preferences.setLogin(true)
            signupActivity.updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(
                SignupViewModel::class.java.simpleName,
                "signInResult:failed code=" + e.statusCode
            )
            signupActivity.updateUI(null)
        }
    }

}