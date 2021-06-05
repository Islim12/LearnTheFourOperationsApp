package com.islimakkaya.learnthefouroperations.entity

import java.io.Serializable

data class Scores(var score_addition:Int,
                  var score_subtraction:Int,
                  var score_multiplication:Int,
                  var score_division:Int): Serializable {
}