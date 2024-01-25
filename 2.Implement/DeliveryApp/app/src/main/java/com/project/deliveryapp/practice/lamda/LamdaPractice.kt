package com.project.deliveryapp.practice.lamda




class LamdaPractice {
    fun doMain() {
        val foo: String? = null

        val upper: String? = foo?.let(String::uppercase)
    }



    fun makeAnonymous() {
        val comp: Comparator1<Int> = object : Comparator1<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                return o1.compareTo(o2)
            }

            override fun baseFun() {
                super.baseFun()
                println("Changing A Base Function.")
            }
        }

        val test: Test<Int> = object : Test<Int> {
            override val o1 = 3
            override val o2 = 4
        }

        test.o1.apply {

        }
    }

}