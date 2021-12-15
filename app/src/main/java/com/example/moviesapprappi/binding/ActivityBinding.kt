package com.example.moviesapprappi.binding

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.example.moviesapprappi.R
import com.example.moviesapprappi.extensions.getStatusBarSize
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar

object ActivityBinding {

    private fun AppCompatActivity.simpleToolbarWithHome(toolbar: MaterialToolbar, title_: String = "") {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            title = title_
        }
        if (toolbar.layoutParams is CollapsingToolbarLayout.LayoutParams) {
            toolbar.layoutParams = (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
                topMargin = getStatusBarSize()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("simpleToolbarWithHome", "simpleToolbarTitle")
    fun bindToolbarWithTitle(toolbar: MaterialToolbar, activity: AppCompatActivity, title: String) {
        activity.simpleToolbarWithHome(toolbar, title)
    }
}