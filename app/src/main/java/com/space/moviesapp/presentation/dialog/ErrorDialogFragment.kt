package com.space.moviesapp.presentation.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.space.moviesapp.R
import com.space.moviesapp.databinding.LayoutErrorBinding

class ErrorDialogFragment(context: Context, private val onRefreshClick: (() -> Unit),) : DialogFragment() {

    private val binding = LayoutErrorBinding.inflate(LayoutInflater.from(context))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(STYLE_NO_TITLE, R.style.MyDialog)
        return inflater.inflate(R.layout.layout_error, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)

        binding.refreshLinearLayout.setOnClickListener {
            onRefreshClick.invoke()
        }
    }
}