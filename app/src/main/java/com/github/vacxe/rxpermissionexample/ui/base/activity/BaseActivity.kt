package com.github.vacxe.rxpermissionexample.ui.base.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.vacxe.rxpermissionexample.ui.base.contracts.BaseViewContract

abstract class BaseActivity : AppCompatActivity(), BaseViewContract {
    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}