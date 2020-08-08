package com.example.triviaapp.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.triviaapp.ui.activity.MainActivity

abstract class BaseFragment(layResourceId: Int) : Fragment(layResourceId) {

    lateinit var mContext: Activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = context as MainActivity

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}