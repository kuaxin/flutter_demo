package com.youjiaoyule.mvvmactual

import org.junit.Test
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.util.*
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val testUnit = TestUnit()
        testUnit.main()
    }


    fun countAndSay(n: Int): String{
        if(n == 1){
            return "1"
        }else{
            var count = 0

            val preStr = countAndSay(n - 1)
            val sb = StringBuilder()

            var num = preStr[0]

            preStr.forEachIndexed { index, c ->
                if(c == num){
                    count++
                }else{
                    sb.append(count)
                    sb.append(num)
                    num = c
                    count = 1
                }
            }

            sb.append(count)
            sb.append(num)
            return sb.toString()
        }
    }

    //求回文字符串
    fun isPalindrome(s: String): Boolean {
        val toCharArray = s.toLowerCase().toCharArray()

        var low = 0
        var high = toCharArray.size - 1

        while(true){
            if(low >= high){
                return true
            }

            //判断
            if(!(toCharArray[low].isDigit() || toCharArray[low].isLetter())){
                low++
                continue
            }

            if(!(toCharArray[high].isDigit() || toCharArray[high].isLetter())){
                high--
                continue
            }

            if(toCharArray[low] == toCharArray[high]){
                low++
                high--
            }else{
                return false
            }
        }
    }


}


interface MediaPlayer{
    fun player(audioType: String,fileName: String)
}

interface AdVanceMediaPlayer{
    fun music(fileName: String)
    fun mp4(fileName: String)
}

class MusicPlayer: AdVanceMediaPlayer{
    override fun music(fileName: String) {
        print("music $fileName")
    }

    override fun mp4(fileName: String) {
    }
}

class VideoPlayer: AdVanceMediaPlayer{
    override fun music(fileName: String) {

    }

    override fun mp4(fileName: String) {
        print("video $fileName")
    }
}

class MediaAdapter(audioType: String) : MediaPlayer{
    private val adVanceMediaPlayer: AdVanceMediaPlayer

    init {
        if(audioType == "music"){
            this.adVanceMediaPlayer = MusicPlayer()
        }else{
            this.adVanceMediaPlayer = VideoPlayer()
        }
    }

    override fun player(audioType: String, fileName: String) {
        if(audioType == "music"){
            adVanceMediaPlayer.music(fileName)
        }else{
            adVanceMediaPlayer.mp4(fileName)
        }
    }
}

class AudioPlayer: MediaPlayer{

    var mediaAdapter: MediaAdapter? = null

    override fun player(audioType: String, fileName: String) {
        mediaAdapter = MediaAdapter(audioType)

        //mediaAdapter   适配器中将支持两种类型的播放 调用不同的接口 从而进行适配
        mediaAdapter?.player(audioType,fileName)
    }

}


fun minSubArrayLen(s: Int, nums: IntArray): Int {

    val n = nums.size
    if(n == 0){
        return 0
    }

    var sum = 0

    var ans = Int.MAX_VALUE

    var start = 0
    var end = 0

    while (end < n){
        sum += nums[end]

        while(sum >= s){
            sum -= nums[start]
            //找最小值
            ans = ans.coerceAtMost(end - start + 1)
            start++
        }

        end++
    }

    return if(ans == Int.MAX_VALUE){
        0
    }else{
        ans
    }

}


//装饰者模式
interface Shape{
    fun draw()
}

class Circle: Shape{
    override fun draw() {
        println("draw circle")
    }
}

abstract class ShapeDecorator(private val decoratedShape: Shape) : Shape

//在这里就可以重新
class RedShapeDecorator(private val decoratedShape: Shape): ShapeDecorator(decoratedShape) {
    override fun draw() {
        decoratedShape.draw()
        setRedBorder(decoratedShape)
    }

    private fun setRedBorder(decoratedShape: Shape) {
        print("设置红色边框")
    }
}


interface Image{
    fun display()
}

class RealImage(): Image{

    private fun loadFromDisk(fileName: String) {

    }

    override fun display() {
        print("我是真实的图片")
    }

}

class ImageProxyFactory(private val img: Any): InvocationHandler{

    @Throws(Throwable::class)
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        method?.invoke(img,args)
        return null
    }

}


class CQueue{
    private val stackPop:Stack<Int> = Stack()
    private val stackPush:Stack<Int> = Stack()


    fun appendTail(value: Int) {
        stackPush.push(value)
    }

    fun deleteHead(): Int {
        if(stackPop.isEmpty()){
            while(!stackPush.isEmpty()){
                stackPop.push(stackPush.pop())
            }
        }

        return if(stackPop.isEmpty()){
            -1
        }else{
            stackPop.pop()
        }
    }

}


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class Solution {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {

        val intList = ArrayList<Int>()

        intList.run {

            matrix.forEachIndexed { index, ints ->
                ints.forEachIndexed { index, i ->
                    add(i)
                }
            }

            sort()
        }

        return if(k > intList.size){
            0
        }else{
            intList[k - 1]
        }
    }


    fun longestValidParentheses(s: String): Int {
        var maxans = 0
        val stack = Stack<Int>()
        val toCharArray = s.toCharArray()

        stack.push(-1)
        toCharArray.forEachIndexed { index, c ->
            if(c == '(') {
                stack.push(index)
            } else {
                stack.pop()
                if(stack.isNotEmpty()){
                    maxans = maxans.coerceAtLeast(index - stack.peek())
                }else{
                    stack.push(index)
                }
            }
        }

        return maxans

    }


    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {

        if(obstacleGrid.isNullOrEmpty()){
            return 0
        }

        val m = obstacleGrid.size
        val n = obstacleGrid[0].size


        var f = IntArray(m)

        f[0] = if(obstacleGrid[0][0] == 0){
            1
        }else{
            0
        }

        for(i in 0 until n){
            for(j in 0 until m){
                if(obstacleGrid[i][j] == 1){
                    f[j] = 0
                    continue
                }
                if(j - 1 >= 0 && obstacleGrid[i][j - 1] == 0){
                    f[j] += f[j - 1]
                }
            }
        }

        return f[m -1]

    }

}



