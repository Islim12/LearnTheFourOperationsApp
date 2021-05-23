package org.islimakkaya.samples.application.learnthefouroperations.util

class NumberUtil {
    companion object {
        fun isFactor(num: Int, divisor: Int): Boolean {
            return when {
                divisor > num/2 -> false
                num % divisor == 0 -> true
                else -> false
            }
        }
    }
}

