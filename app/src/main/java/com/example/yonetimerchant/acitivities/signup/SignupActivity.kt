package com.example.yoneti.activities.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.yoneti.activities.login.LoginActivity
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.acitivities.otp.OTPActivity
import com.example.yonetimerchant.databinding.ActivitySignUpBinding
import com.example.yonetimerchant.utils.openActivity
import com.example.yonetimerchant.utils.showToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var signUpBinding: ActivitySignUpBinding
    val signUpViewModel: SignupViewModel by viewModels()
    private val RC_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        signUpBinding.signUpViewModelX = signUpViewModel

//        signUpBinding.btnSignup.setOnClickListener {
//            openActivity(HomeActivity::class.java)
//            finish()
//        }

        /**
         * Sign in With Google
         */
        signUpViewModel.getGoogleSigninClient(this)
        signUpBinding.btnGoogle.setOnClickListener(this)
        signUpBinding.btnFacebook.setOnClickListener(this)

        /**
         * checking if fields are empty and showing errors
         */
        signUpViewModel.emailObserver.observe(this, Observer {
            if (it)
                signUpBinding.etSignupEmail.error = "Provide Email"
        })
        signUpViewModel.phoneObserver.observe(this, Observer {
            if (it)
                signUpBinding.etPhoneNumber.error = "Provide Phone"
        })
        signUpViewModel.passwordObserver.observe(this, Observer {
            if (it)
                signUpBinding.etPassword.error = "Provide Password"
        })

        signUpViewModel.verifyUser.observe(this, Observer {
            openActivity(OTPActivity::class.java)
            finish()
        })

        /**
         * show or hide the progress bar
         */
        signUpViewModel.isRequesting.observe(this, Observer {
            if (it)
                signUpBinding.signUpBar.visibility = View.VISIBLE
            else {
                signUpBinding.signUpBar.visibility = View.GONE
                if (signUpViewModel.message != null && signUpViewModel.message?.isNotEmpty()!!)
                showToast(signUpViewModel.message!!)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        signUpViewModel.ifUserExistsOrNot(this)
    }

    fun onLoginclick(view: View) {
        openActivity(LoginActivity::class.java)
        finish()
    }

    fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            openActivity(HomeActivity::class.java)
            finish()
        } else
            Toast.makeText(this, "Google login went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnGoogle -> {
                val signInIntent: Intent = signUpViewModel.mGoogleSignInClient?.getSignInIntent()!!
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
            R.id.btnFacebook -> {
                Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_SIGN_IN -> {
                if (resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    signUpViewModel.handleSignInResult(task, this)
                }
            }
        }
    }
}