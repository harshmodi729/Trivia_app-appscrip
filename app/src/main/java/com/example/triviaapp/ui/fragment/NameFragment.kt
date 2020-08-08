package com.example.triviaapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.triviaapp.R
import com.example.triviaapp.base.BaseFragment
import com.example.triviaapp.base.BaseResult
import com.example.triviaapp.extension.isEmptyOrBlank
import com.example.triviaapp.extension.makeToast
import com.example.triviaapp.viewmodel.DataViewModel
import com.example.triviaapp.viewmodel.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_name.*

class NameFragment : BaseFragment(R.layout.fragment_name) {

    private lateinit var triviaViewModel: TriviaViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        triviaViewModel = ViewModelProviders.of(requireActivity())[TriviaViewModel::class.java]
        val dataViewModel = ViewModelProviders.of(this)[DataViewModel::class.java]
        btnNext.setOnClickListener {
            moveNext(edName.text.toString().trim())
        }
        btnHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_nameFragment_to_historyFragment)
        }

        dataViewModel.getAllTrivia(mContext)
        dataViewModel.listTriviaLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BaseResult.Success -> {
                    if (it.item.isNotEmpty())
                        btnHistory.visibility = View.VISIBLE
                    else
                        btnHistory.visibility = View.GONE
                }
                is BaseResult.Error -> {
                    btnHistory.visibility = View.GONE
                }
            }
        })
    }

    /**
     * This method will check whether name [String] is empty or blank and returns [String]
     * message in response as [LiveData]
     * @param name [String] will be check is it empty/blank or not
     */
    private fun moveNext(name: String) {
        if (name.isEmptyOrBlank()) {
            mContext.makeToast("Please enter Name.")
        } else {
            triviaViewModel.name = name
            mContext.makeToast("Name saved successfully.")
            btnNext.findNavController()
                .navigate(R.id.action_nameFragment_to_cricketerFragment)
        }
    }
}