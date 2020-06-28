package com.youjiaoyule.mvvmactual

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
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