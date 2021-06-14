package com.wat.rentahome.models

import java.util.*

data class Offer(
    val userDto: User,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val dateOfCreate: Date?
)
