package com.example.dashbordminiproject.models

data class CompleteData(
    var Citylist: List<City>,
    var Message: String,
    var Status: Boolean
) {
    override fun toString(): String {
        return "$Citylist $Message $Status"
    }
}