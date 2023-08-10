package com.space.moviesapp.common.maper

interface Mapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}