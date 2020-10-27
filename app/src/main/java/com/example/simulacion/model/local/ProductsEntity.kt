package com.example.simulacion.model.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


class ProductsEntity (
    @PrimaryKey  @NonNull val id: Int,
    val image: String,
    val name: String,
    val price: Int
)