package com.teckathon.missingpeopleapp.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.data.database.entities.User
import com.teckathon.missingpeopleapp.databinding.ActivityLoginBinding
import com.teckathon.missingpeopleapp.ui.activities.home.HomeActivity
import com.teckathon.missingpeopleapp.util.Coroutines
import com.teckathon.missingpeopleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(), DIAware, View.OnClickListener {

    override val di: DI by lazy { (applicationContext as DIAware).di }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    private val factory: AuthViewModelFactory by instance()

    private lateinit var auth: FirebaseAuth

    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        Coroutines.readWrite {
            viewModel.storeUser(User(1223,"Default","default@mail.com","123","","",""))
        }

        auth = FirebaseAuth.getInstance()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                updateUI(STATE_VERIFY_SUCCESS, credential)
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false

                if (e is FirebaseAuthInvalidCredentialsException) {
                    toast("Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT).show()
                }

                updateUI(STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")

                storedVerificationId = verificationId
                resendToken = token

                updateUI(STATE_CODE_SENT)
            }
        }

        binding.loginButton.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
        if (verificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(binding.mobileEditText.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, verificationInProgress)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        verificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS)
    }

    /**
     *
     * @param phoneNumber String
     */
    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks
        )
        verificationInProgress = true
    }

    /**
     *
     * @param verificationId String?
     * @param code String
     */
    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    /**
     *
     * @param credential PhoneAuthCredential
     */
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    updateUI(STATE_SIGNIN_SUCCESS, user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        toast("Invalid code.")
                    }
                    updateUI(STATE_SIGNIN_FAILED)
                }
            }
    }

    /**
     *
     * @param user FirebaseUser?
     */
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user)
        } else {
            updateUI(STATE_INITIALIZED)
        }
    }

    /**
     *
      * @param uiState Int
     * @param cred PhoneAuthCredential
     */
    private fun updateUI(uiState: Int, cred: PhoneAuthCredential) {
        updateUI(uiState, null, cred)
    }

    /**
     *
     * @param uiState Int
     * @param user FirebaseUser?
     * @param cred PhoneAuthCredential?
     */
    private fun updateUI(
        uiState: Int,
        user: FirebaseUser? = auth.currentUser,
        cred: PhoneAuthCredential? = null
    ) {

        when (uiState) {
            STATE_INITIALIZED -> {

            }
            STATE_CODE_SENT -> {

            }
            STATE_VERIFY_FAILED -> {

            }
            STATE_VERIFY_SUCCESS -> {
                loginButton.setText("Verify")
                subTitle.setText("Please enter the code!")
            }
            STATE_SIGNIN_FAILED ->
                // No-op, handled by sign-in check
                toast("Sign in failed!")
            STATE_SIGNIN_SUCCESS -> {
            }
        } // Np-op, handled by sign-in check

        if (user != null) {
            Intent(this, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    /**
     *
     * @return Boolean
     */
    private fun validatePhoneNumber(): Boolean {
        return true
    }

    /**
     *
     * @param view View
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.loginButton -> {
                if (loginButton.text.toString() == "Continue") {
                    if (!validatePhoneNumber()) {
                        return
                    }
                    extensionEditText.visibility = View.INVISIBLE
                    startPhoneNumberVerification(binding.extensionEditText.text.toString() + binding.mobileEditText.text.toString())
                } else {
                    val code = binding.mobileEditText.text.toString()
                    if (TextUtils.isEmpty(code)) {
                        toast("Cannot be empty.")
                        return
                    }

                    verifyPhoneNumberWithCode(storedVerificationId, code)
                }
            }
        }
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
        private const val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
        private const val STATE_INITIALIZED = 1
        private const val STATE_VERIFY_FAILED = 3
        private const val STATE_VERIFY_SUCCESS = 4
        private const val STATE_CODE_SENT = 2
        private const val STATE_SIGNIN_FAILED = 5
        private const val STATE_SIGNIN_SUCCESS = 6
    }

}