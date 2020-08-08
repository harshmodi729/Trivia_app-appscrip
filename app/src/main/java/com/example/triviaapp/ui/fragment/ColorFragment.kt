package com.example.triviaapp.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.triviaapp.R
import com.example.triviaapp.base.BaseFragment
import com.example.triviaapp.extension.makeToast
import com.example.triviaapp.viewmodel.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_color.*
import kotlinx.android.synthetic.main.fragment_cricketer.btnNext

class ColorFragment : BaseFragment(R.layout.fragment_color) {

    private lateinit var triviaViewModel: TriviaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        triviaViewModel = ViewModelProviders.of(requireActivity())[TriviaViewModel::class.java]

        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        activity?.onBackPressedDispatcher!!.addCallback(viewLifecycleOwner, backButtonCallback)

        btnNext.setOnClickListener {
            validateControl(
                chkWhite.isChecked, chkYellow.isChecked,
                chkOrange.isChecked, chkGreen.isChecked
            )
        }
    }

    /**
     * This method will check whether correct [CheckBox]es are checked or not
     * @param chkWhite
     * @param chkYellow
     * @param chkOrange
     * @param chkGreen
     */
    private fun validateControl(
        chkWhite: Boolean,
        chkYellow: Boolean,
        chkOrange: Boolean,
        chkGreen: Boolean
    ) {
        if (chkYellow) {
            mContext.makeToast("Wrong item selected. Yellow color is not in Indian Flag.")
        } else {
            val colorList = ArrayList<String>()
            if (chkWhite)
                colorList.add("White")
            if (chkOrange)
                colorList.add("Orange")
            if (chkGreen)
                colorList.add("Green")
            triviaViewModel.colors = TextUtils.join(",", colorList)
            mContext.makeToast("Colors saved successfully.")
            btnNext.findNavController()
                .navigate(R.id.action_colorFragment_to_finishFragment)
        }
    }
}