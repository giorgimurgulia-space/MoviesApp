package com.space.moviesapp.presentation.base.useCase

abstract class BaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}
