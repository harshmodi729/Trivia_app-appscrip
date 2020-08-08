package com.example.triviaapp.ui.fragment

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.triviaapp.R
import com.example.triviaapp.base.BaseFragment
import com.example.triviaapp.extension.makeToast
import com.example.triviaapp.viewmodel.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_cricketer.*


class CricketerFragment : BaseFragment(R.layout.fragment_cricketer) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        activity?.onBackPressedDispatcher!!.addCallback(viewLifecycleOwner, backButtonCallback)

        val triviaViewModel = ViewModelProviders.of(requireActivity())[TriviaViewModel::class.java]
        btnNext.setOnClickListener {
            if (rgCricketer.checkedRadioButtonId != -1) {
                mContext.makeToast("Best cricketer saved successfully.")
                it.findNavController().navigate(R.id.action_cricketerFragment_to_colorFragment)
            } else
                mContext.makeToast("Please select any option.")
        }

        rgCricketer.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbtnSachin -> {
                    triviaViewModel.bestCricketer = mContext.getString(R.string.sachin_tendulkar)
                }
                R.id.rbtnVirat -> {
                    triviaViewModel.bestCricketer = mContext.getString(R.string.virat_kohli)
                }
                R.id.rbtnAdam -> {
                    triviaViewModel.bestCricketer = mContext.getString(R.string.adam_gilchrist)
                }
                R.id.rbtnJacques -> {
                    triviaViewModel.bestCricketer = mContext.getString(R.string.jacques_kallis)
                }
            }
        }
    }
}