package com.wat.rentahome.models

import java.util.*

data class User (
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val dateOfBirth: Date?,
    val dateOfJoin: Date?
)