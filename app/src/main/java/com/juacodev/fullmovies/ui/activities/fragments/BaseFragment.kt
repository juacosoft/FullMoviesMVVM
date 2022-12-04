package com.juacodev.fullmovies.ui.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment  <DB : ViewBinding> (
    private val inflate: (inflater: LayoutInflater) -> DB
): Fragment() {
    private var _binding: DB? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater)
        onViews()
        onViewModels()
        when(_binding){
            is ViewBinding ->{
                return binding.root
            }
            else -> throw IllegalArgumentException("Binding cannot be null")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    abstract fun onViews()
    abstract fun onViewModels()
}