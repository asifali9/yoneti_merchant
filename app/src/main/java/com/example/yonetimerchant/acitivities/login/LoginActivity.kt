package com.example.yoneti.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.yoneti.activities.signup.SignupActivity
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.acitivities.otp.OTPActivity
import com.example.yonetimerchant.databinding.ActivityLoginBinding
import com.example.yonetimerchant.utils.hide
import com.example.yonetimerchant.utils.openActivity
import com.example.yonetimerchant.utils.show
import com.example.yonetimerchant.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var isUpdatePassword: Boolean = false
    private lateinit var loginBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.loginViewModel = viewModel
        isUpdatePassword = intent.getBooleanExtra("isUpdatePassword",false);
        viewModel.hasForgotPassword.observe(this, Observer {
            if (it) {
                loginBinding.btnLogin.visibility = View.GONE
                loginBinding.etPassword.visibility = View.GONE
                loginBinding.tvForgotPasswordHyperLink.visibility = View.GONE
                loginBinding.btnSubmitEmail.visibility = View.VISIBLE
                loginBinding.tvLoginHeader.text = getString(R.string.forgot_password)
                loginBinding.tvSubHeading.text = getString(R.string.forgot_password_subheading)
            }
        })
        loginBinding.btnLogin.setOnClickListener {
            if (isUpdatePassword)
                viewModel.forgotPassword()
            else
                viewModel.loginUser()
        }
//        loginBinding.btnLogin.setOnClickListener {
//            var intent = Intent(this, SignupActivity::class.java)
//            var option = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this,
//                loginBinding.btnLogin as View,
//                "shared"
//            )
//            startActivity(intent, option.toBundle())
//            openActivity(SignupActivity::class.java)
//            finish()
//        }

        viewModel.isOtpFound.observe(this, Observer {
            if (it)
                openActivity(OTPActivity::class.java)
        })

        viewModel.isLoading.observe(this, Observer {
            if (it)
                loginBinding.progressBar.show()
            else {
                loginBinding.progressBar.hide()
                showToast(viewModel.loginErrorMessage!!)
            }
        })

        viewModel.isLoginSuccessful.observe(this, Observer {
            if (it)
            {
                openActivity(HomeActivity::class.java)
                finish()
            }
        })

        viewModel.emptyPasswordField.observe(this, Observer {
            if (it)
                loginBinding.etPassword.error = "Provide Password"
        })
        viewModel.emptyEmailField.observe(this, Observer {
            if (it)
                loginBinding.etEmail.error = "Provide Email"
        })

        viewModel.otpMessage.observe(this, Observer {
            showToast(it)
        })
    }

    fun settingViewsForPasswordForgot() {
        loginBinding.btnLogin.text = getString(R.string.submit)
        loginBinding.tvLoginHeader.text = getString(R.string.forgot_password)
        loginBinding.tvLoginHeader.text = getString(R.string.forgot_password_subheading)
    }

    override fun onBackPressed() {
        if (viewModel.hasForgotPassword.value == true) {
            loginBinding.btnLogin.visibility = View.VISIBLE
            loginBinding.etPassword.visibility = View.VISIBLE
            loginBinding.tvForgotPasswordHyperLink.visibility = View.VISIBLE
            loginBinding.btnSubmitEmail.visibility = View.GONE
            loginBinding.tvLoginHeader.text = getString(R.string.login_heading)
            loginBinding.tvSubHeading.text = getString(R.string.sign_in_to_continue)
            viewModel.hasForgotPassword.value = false
        } else
            super.onBackPressed()

    }

    fun onSignupClick(view: View) {
        openActivity(SignupActivity::class.java)
        finish()
    }
}