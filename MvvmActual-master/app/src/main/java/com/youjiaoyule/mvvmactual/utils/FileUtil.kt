package com.youjiaoyule.mvvmactual.utils

import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import java.io.*
import java.nio.charset.StandardCharsets

/**
 *  @author RenGX on 2020/7/6
 *
 */
object FileUtil {
    val filename = "myfile.txt"
    val fileContents = "Hello world!"
    fun writeFile(context: Context){
        var standardDirectory: File?
        var dirPath = ""
        var dirName = ""
        if (TextUtils.isEmpty(filename)) {
            Log.e(TAG, "saveImage2SandBox: fileName is null or image is null!");
            return;
        }

        if (!TextUtils.isEmpty("wdkid/data/txt/")) {
            standardDirectory = context.getExternalFilesDir("wdkid/data/txt/");
        } else {
            standardDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }

//        val freeSpace = standardDirectory?.freeSpace
//        val usableSpace = standardDirectory?.usableSpace
//        val totalSpace = standardDirectory?.totalSpace
//
//        Log.d("RGX","$freeSpace -- $usableSpace -- $totalSpace")
//
//
//        if (!TextUtils.isEmpty(dirName)) {
//            dirPath = "$standardDirectory/$dirName";
//        } else {
//            dirPath = java.lang.String.valueOf(standardDirectory)
//        }

//        var imageFileDirctory = File(dirPath);
//        if (!imageFileDirctory.exists()) {
//            if (!imageFileDirctory.mkdir()) {
//                Log.e(TAG, "saveImage2SandBox: mkdir failed! Directory: " + dirPath);
//                return;
//            }
//        }
//
////        if (queryImageFromSandBox(context, fileName, environmentType, dirName)) {
////            Log.e(TAG, "saveImage2SandBox: The file with the same name already exists！");
////            return;
////        }
//
//        try {
//            var imageFile = File("$dirPath/$filename");
//            var fileOutputStream = FileOutputStream(imageFile);
//            fileOutputStream.write(fileContents.toByteArray());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (e: IOException) {
//            e.printStackTrace();
//        }
    }

    fun readFile(context: Context){
        val fis: FileInputStream = context.openFileInput(filename)
        val inputStreamReader = InputStreamReader(fis, StandardCharsets.UTF_8)
        val stringBuilder = StringBuilder()
        try {
            BufferedReader(inputStreamReader).use { reader ->
                var line: String = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line).append('\n')
                    line = reader.readLine()
                }
            }
        } catch (e: IOException) {
            // Error occurred when opening raw file for reading.
        } finally {
            val contents = stringBuilder.toString()
            print(contents)
        }
    }





}