package com.ammase.androidmvvmdaggerrx.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ammase.androidmvvmdaggerrx.utils.Status
import com.ammase.androidmvvmdaggerrx.baseview.BaseActivity
import com.ammase.androidmvvmdaggerrx.databinding.ActivityMainBinding
import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.ammase.androidmvvmdaggerrx.ui.viewmodel.MainViewModel
import com.ammase.androidmvvmdaggerrx.utils.Encoding.md5
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun statusBarColor(): Int = 0
    private val mainViewModel: MainViewModel by viewModels()

    override fun setup(savedInstanceState: Bundle?) {

        setupAPICall()

        binding.run {
            button.setOnClickListener {
                var email = editTextTextEmail.text.toString()
                var pass = editTextTextPassword.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this@MainActivity, "email kosong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (pass.isEmpty()) {
                    Toast.makeText(this@MainActivity, "password kosong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                mainViewModel.postLogin(
                    JsonObject().apply {
                        addProperty("username", email)
                        addProperty("password", pass.md5())
                    }
                )
            }
        }

    }
    private fun setupAPICall() {
        mainViewModel.getLogin().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, "sukses login", Toast.LENGTH_LONG).show()
                    renderLogin(it.data!!.data)
                }
                Status.LOADING -> {
                    Toast.makeText(this, "loading", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_LONG).show()
                }
                Status.INTERNET -> {
                    Toast.makeText(this, "Internet ${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderLogin(data: SignResponseModel.Data) {
        Toast.makeText(this, "${data.name}", Toast.LENGTH_LONG).show()
    }

}