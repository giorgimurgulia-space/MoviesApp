package com.space.moviesapp.presentation.model

sealed class DialogItem(val viewType: ViewType) {
    enum class ViewType {
        LOADER,
        ERROR
    }

    data class LoaderDialog(
        val isLoaded: Boolean = true
    ) : DialogItem(ViewType.LOADER)

    data class ErrorDialog(
        val onRefreshClick: (() -> Unit)
    ) : DialogItem(ViewType.ERROR)
}