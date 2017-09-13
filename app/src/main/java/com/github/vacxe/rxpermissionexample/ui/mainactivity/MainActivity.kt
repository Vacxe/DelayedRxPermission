package com.github.vacxe.rxpermissionexample.ui.mainactivity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.vacxe.rxpermissionexample.R
import com.github.vacxe.rxpermissionexample.interactors.PresentationInteractor
import com.github.vacxe.rxpermissionexample.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.content_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), Contract.View {

    val presenter = MainActivityPresenter(PresentationInteractor(WeakReference(this), this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.view = this

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { presenter.doPresentationAction() }
    }

    override fun showLoader() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader.visibility = View.GONE
    }
}
