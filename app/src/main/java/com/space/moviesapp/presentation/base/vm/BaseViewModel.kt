package com.space.moviesapp.presentation.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.moviesapp.common.types.MovieEvent
import com.space.moviesapp.presentation.model.DialogItem


abstract class BaseViewModel : ViewModel() {
    private val _dialog = MutableLiveData<MovieEvent<DialogItem>>()
    val dialog get() = _dialog

    fun setDialog(dialog: DialogItem) {
        _dialog.value = MovieEvent(dialog)
    }

    fun closeLoaderDialog() {
        _dialog.value = MovieEvent(DialogItem.LoaderDialog(false))
    }
}