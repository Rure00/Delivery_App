package com.project.deliveryapp.practice.lamda

interface Comparator1<Int> {
    fun compare(o1: Int, o2: Int ): Int

    fun baseFun() {
        println("It is Base Function.")
    }
}