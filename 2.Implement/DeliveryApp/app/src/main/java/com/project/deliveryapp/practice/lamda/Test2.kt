package com.project.deliveryapp.practice.lamda

class Test2 {

    fun doMain() {
        val blcok = {a: Int, b: Int ->
            a+b
        }
        sum(3,4, blcok)  { c: Boolean ->
            !c
        }
    }

    inline fun sum(a: Int, b:Int, block: (Int, Int) -> Int, block2: (Boolean) -> Boolean): Int {
        return block(a,b)
    }
}