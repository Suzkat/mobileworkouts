package com.ebookfrenzy.finalproject

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ebookfrenzy.finalproject.R.layout.activity_anonymous_auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_anonymous_auth.*


class AnonymousLogin : BaseActivity(), View.OnClickListener {

    private var mAuth: FirebaseAuth? = null

    private var mEmailField: EditText? = null
    private var mPasswordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_auth)

        mAuth = FirebaseAuth.getInstance()

        // Fields
        mEmailField = findViewById<EditText>(R.id.field_email)
        mPasswordField = findViewById(R.id.field_password)

        // Click listeners
        findViewById<View>(R.id.button_anonymous_sign_in).setOnClickListener(this)
        findViewById<View>(R.id.button_anonymous_sign_out).setOnClickListener(this)
        findViewById<View>(R.id.button_link_account).setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
        signInAnonymously()
    }

    private fun signInAnonymously() {
        showProgressDialog()
        mAuth!!.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(this@AnonymousLogin, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    hideProgressDialog()
                }

    }

    private fun signOut() {
        mAuth!!.signOut()
        updateUI(null)
    }

    private fun linkAccount() {
        // Make sure form is valid
        if (!validateLinkForm()) {
            return
        }

        // Get email and password from form
        val email = mEmailField!!.text.toString()
        val password = mPasswordField!!.text.toString()

        // Create EmailAuthCredential with email and password
        val credential = EmailAuthProvider.getCredential(email, password)

        // Link the anonymous user to the email credential
        showProgressDialog()


        mAuth!!.currentUser!!.linkWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "linkWithCredential:success")
                        val user = task.result.user
                        updateUI(user)
                        val loginIntent = Intent(this@AnonymousLogin,UserOptions::class.java)
                       // loginIntent.putExtra("email",email)
                       // loginIntent.putExtra("password",password)
                        //var username: TextView= findViewById(R.id.userprofilename)
//                        if(email!=null)
//                        {
//
//                            username.text = "Welcome back, " + email
//                        }
                        //username.text = "Welcome back, " + email
                        startActivity(loginIntent)

                    } else {
                        Log.w(TAG, "linkWithCredential:failure", task.exception)
                        Toast.makeText(this@AnonymousLogin, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }


                    hideProgressDialog()

                }

    }

    private fun validateLinkForm(): Boolean {
        var valid = true

        val email = mEmailField!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            mEmailField!!.error = "Required."
            valid = false
        } else {
            mEmailField!!.error = null
        }

        val password = mPasswordField!!.text.toString()
        if (TextUtils.isEmpty(password)) {
            mPasswordField!!.error = "Required."
            valid = false
        } else {
            mPasswordField!!.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()

        val idView = findViewById<TextView>(R.id.anonymous_status_id)
        val emailView = findViewById<TextView>(R.id.anonymous_status_email)
        val isSignedIn = user != null

        // Status text
        if (isSignedIn) {
            idView.text = getString(R.string.id_fmt, user!!.uid)
            emailView.text = getString(R.string.email_fmt, user.email)

        } else {
            idView.setText(R.string.signed_out)
            emailView.text = null
        }

        // Button visibility
        findViewById<View>(R.id.button_anonymous_sign_in).isEnabled = !isSignedIn
        findViewById<View>(R.id.button_anonymous_sign_out).isEnabled = isSignedIn
        findViewById<View>(R.id.button_link_account).isEnabled = isSignedIn
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.button_anonymous_sign_in) {
            signInAnonymously()
        } else if (i == R.id.button_anonymous_sign_out) {
            signOut()
        } else if (i == R.id.button_link_account) {
            linkAccount()
        }
    }

    companion object {

        private val TAG = "AnonymousAuth"
    }
}
