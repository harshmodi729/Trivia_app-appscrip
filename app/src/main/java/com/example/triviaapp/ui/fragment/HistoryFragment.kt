package com.example.triviaapp.ui.fragment

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.triviaapp.R
import com.example.triviaapp.adapter.HistoryAdapter
import com.example.triviaapp.base.BaseFragment
import com.example.triviaapp.base.BaseResult
import com.example.triviaapp.model.TriviaItem
import com.example.triviaapp.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    private lateinit var dataViewModel: DataViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        activity?.onBackPressedDispatcher!!.addCallback(viewLifecycleOwner, backButtonCallback)

        dataViewModel = ViewModelProviders.of(this)[DataViewModel::class.java]

        val adapter = HistoryAdapter(mContext)
        rvHistory.adapter = adapter

        dataViewModel.getAllTrivia(mContext)
        dataViewModel.listTriviaLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BaseResult.Success -> {
                    adapter.addData(it.item as ArrayList<TriviaItem>)
                }
            }
        })
    }
}