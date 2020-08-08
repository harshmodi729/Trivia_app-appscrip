package com.example.triviaapp.ui.fragment

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.triviaapp.R
import com.example.triviaapp.base.BaseFragment
import com.example.triviaapp.base.BaseResult
import com.example.triviaapp.extension.makeToast
import com.example.triviaapp.model.TriviaItem
import com.example.triviaapp.viewmodel.DataViewModel
import com.example.triviaapp.viewmodel.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishFragment : BaseFragment(R.layout.fragment_finish) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val triviaViewModel = ViewModelProviders.of(requireActivity())[TriviaViewModel::class.java]
        val dataViewModel = ViewModelProviders.of(this)[DataViewModel::class.java]
        dataViewModel.addTriviaLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BaseResult.Success -> {
                    mContext.makeToast(it.item)
                    findNavController().navigate(R.id.action_finishFragment_to_nameFragment)
                }
                is BaseResult.Error -> {
                    mContext.makeToast(it.errorMessage)
                }
            }
        })

        tvName.text = getString(R.string.hello_suffix).plus(triviaViewModel.name)
        tvAns2.text = getString(R.string.answer_suffix).plus(triviaViewModel.bestCricketer)
        tvAns3.text = getString(R.string.multiple_answer_suffix).plus(triviaViewModel.colors)

        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        activity?.onBackPressedDispatcher!!.addCallback(viewLifecycleOwner, backButtonCallback)

        btnFinish.setOnClickListener {
            val item = TriviaItem()
            item.name = triviaViewModel.name
            item.bestCricketer = triviaViewModel.bestCricketer
            item.colors = triviaViewModel.colors
            val calendar = Calendar.getInstance()
            val dateTime =
                SimpleDateFormat("dd, MMMM yyyy hh:mm a", Locale.US).format(calendar.time)
            item.time = dateTime
            dataViewModel.insert(mContext, item)
        }
    }
}