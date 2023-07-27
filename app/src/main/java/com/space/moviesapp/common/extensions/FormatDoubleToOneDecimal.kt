package com.space.moviesapp.common.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Double.formatDoubleToOneDecimal() =
    BigDecimal(this).setScale(1, RoundingMode.HALF_EVEN).toDouble()
