package com.ajin.demo

import org.junit.Test

import org.junit.Assert.*
import java.text.CharacterIterator

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("UNUSED_CHANGED_VALUE")
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    fun myAtoi(str: String): Int{

        val toCharArray = str.toCharArray()

        if(toCharArray.isEmpty()){
            return 0
        }

        var index = 0

        while(index < toCharArray.size && toCharArray[index] == ' '){
            index++
        }

        if(index == toCharArray.size){
            return 0
        }

        var isDel = false

        when {
            toCharArray[index] == '-' -> {
                isDel = true
                index++
            }
            toCharArray[index] == '+' -> {
                index++
            }
            !toCharArray[index].isDigit() -> {
                return 0
            }
        }

        var ans = 0

        while(index < toCharArray.size && toCharArray[index].isDigit()){
            var digit = toCharArray[index] - '0'
            if(ans > (Int.MAX_VALUE - digit) / 10){
                return if(isDel){
                    Int.MIN_VALUE
                }else{
                    Int.MAX_VALUE
                }
            }

            ans = ans * 10 + digit

            index++
        }

        return if(isDel){
            -ans
        }else{
            ans
        }

    }


}