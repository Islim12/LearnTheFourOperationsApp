package com.islimakkaya.learnthefouroperations.entity

import java.io.Serializable

data class Categories(var category_id:Int,
                      var category_name:String,
                      var category_image_name:String): Serializable {
}