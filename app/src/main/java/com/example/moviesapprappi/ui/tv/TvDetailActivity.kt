package com.example.moviesapprappi.ui.tv

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.ActivityTvDetailBinding
import com.example.moviesapprappi.model.Tv
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailActivity :
    BindingActivity<ActivityTvDetailBinding>(R.layout.activity_tv_detail) {

    private val vm: TvDetailViewModel by viewModels()
    private val intentTv: Tv by bundleNonNull(TV_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            activity = this@TvDetailActivity
            viewModel = vm.apply { postTvId(intentTv.id) }
            tv = intentTv
            videoAdapter = VideoListAdapter()
            reviewAdapter = ReviewListAdapter()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    companion object {
        private const val TV_ID = "tv"
        fun startActivityModel(context: Context?, tv: Tv?) {
            context?.intentOf<TvDetailActivity> {
                putExtra(TV_ID to tv)
                startActivity(context)
            }
        }
    }
}