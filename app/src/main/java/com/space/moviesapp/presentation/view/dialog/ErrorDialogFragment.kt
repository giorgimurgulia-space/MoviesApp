package com.space.moviesapp.presentation.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.space.moviesapp.R
import com.space.moviesapp.databinding.LayoutErrorBinding

class ErrorDialogFragment(private val onRefreshClick: () -> Unit) : DialogFragment() {

    private var binding: LayoutErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutErrorBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))


        binding?.refreshLinearLayout?.setOnClickListener {
            onRefreshClick()
            this.dismiss()
        }
    }
}
