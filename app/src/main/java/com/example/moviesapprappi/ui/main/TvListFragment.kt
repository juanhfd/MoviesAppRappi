package com.example.moviesapprappi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.MainFragmentTvBinding
import com.example.moviesapprappi.viewmodel.MainActivityViewModel
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvListFragment :
    BindingFragment<MainFragmentTvBinding>(R.layout.main_fragment_tv) {

    private val vm: MainActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            adapter = TvListAdapter()
            viewModel = vm
        }.root
    }
}