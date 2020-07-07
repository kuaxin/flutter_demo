package com.youjiaoyule.mvvmactual.utils

import android.media.AudioFormat.*
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi

/**
 *  @author RenGX on 2020/7/6
 *
 */
object AudioRecordUtil {

    private var recordBufSize = 0
    private var audioRecord: AudioRecord? = null
    var bufferData: ByteArray? = null
    var isRecording = false
    @RequiresApi(Build.VERSION_CODES.N)
    fun createAudioRecord(){
        recordBufSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_UNSPECIFIED,CHANNEL_IN_STEREO,ENCODING_PCM_16BIT)

        audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC,SAMPLE_RATE_UNSPECIFIED,CHANNEL_IN_STEREO,ENCODING_PCM_16BIT,
            recordBufSize)

        bufferData = ByteArray(recordBufSize)
    }


    fun startRecord(){
        audioRecord?.startRecording()
        isRecording = true




    }

    fun stopRecord(){

    }

}