package com.example.carshop
import com.example.carshop.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
interface CarRepository:JpaRepository<Car, UUID>