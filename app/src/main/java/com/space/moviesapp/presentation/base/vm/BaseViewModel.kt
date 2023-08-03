package com.space.moviesapp.presentation.base.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.space.moviesapp.common.types.MovieEvent
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.navigation.NavigationCommand


abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableLiveData<MovieEvent<NavigationCommand>>()
    val navigation: LiveData<MovieEvent<NavigationCommand>> get() = _navigation

    private val _dialog = MutableLiveData<MovieEvent<DialogItem>>()
    val dialog get() = _dialog

    fun setDialog(dialog: DialogItem) {
        _dialog.value = MovieEvent(dialog)
    }

    fun closeLoaderDialog() {
        _dialog.value = MovieEvent(DialogItem.LoaderDialog(false))
    }

    fun navigate(navDirections: NavDirections) {
        _navigation.value = MovieEvent(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigation.value = MovieEvent(NavigationCommand.Back)
    }
}