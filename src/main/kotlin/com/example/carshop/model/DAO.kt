package com.example.carshop.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
data class Car (
    @Id @GeneratedValue val id: UUID,
    val model:String,
    val brand:String,
    val productionYear:Int,
    val price:Double,
    var activeFlag:Boolean
)