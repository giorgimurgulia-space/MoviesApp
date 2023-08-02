package com.space.moviesapp.presentation.dialog

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.space.moviesapp.R
import com.space.moviesapp.databinding.LayoutErrorBinding
import com.space.moviesapp.databinding.LayoutLoaderBinding

class LoaderDialogFragment : DialogFragment() {

    private val binding = LayoutLoaderBinding.inflate(LayoutInflater.from(context))
    private var onRefreshClickListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_loader, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(R.color.neutral_01_black))
    }
}