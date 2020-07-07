package com.youjiaoyule.mvvmactual.activity.home.test

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.base.BaseActivity
import com.youjiaoyule.mvvmactual.base.BaseViewModel
import com.youjiaoyule.mvvmactual.utils.FileUtil
import kotlinx.android.synthetic.main.activity_test.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 *  @author RenGX on 2020/6/29
 *
 */
class TestActivity: BaseActivity() {
    val lis: ArrayList<Uri> by lazy {
        ArrayList<Uri>()
    }
    var adapter: BaseQuickAdapter<Uri,BaseViewHolder>? = null

    fun writeFile(view: View) {
        when(view.id){
            R.id.btn_write ->{
                FileUtil.writeFile(this)
            }
            R.id.btn_read ->{
                FileUtil.readFile(this)
            }
            R.id.btn_find_pic ->{
//                getIDCMUri()
                val imgData = getImgFromMine() ?: return

                val bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.size)
                Glide.with(this).load(bitmap).into(img_title)
            }
            //插入图片
            R.id.btn_insert_pic ->{
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_title)
                val displayName = "${System.currentTimeMillis()}.png"
                val mimeType = "image/png"
                val compressFormat = Bitmap.CompressFormat.PNG
//                saveImgToIDCM(bitmap,displayName,mimeType,compressFormat)
                saveImageToMine(bitmap)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
//        rv_pic.layoutManager = GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false)
//
//        adapter = object : BaseQuickAdapter<Uri, BaseViewHolder>(R.layout.item_rv_pic){
//            override fun convert(holder: BaseViewHolder, item: Uri) {
//                Glide.with(this@TestActivity).load(item).into(holder.getView(R.id.img_pic))
//            }
//        }
//        rv_pic.adapter = adapter

    }

    override fun initDate() {

    }

    override fun onHandlerReceive(msg: Message) {

    }

    //从公共目录获取图片
    fun getIDCMUri(){

        val query = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.MediaColumns.DATE_ADDED} desc"
        )

        if(query != null){
            lis.clear()
            while (query.moveToNext()){
                val id = query.getLong(query.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                val uri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                lis.add(uri)
                Log.d("RGX","image uri is $uri")
            }

            adapter?.addData(lis)
            query.close()
        }
    }

    //存储图片到公共目录
    fun saveImgToIDCM(bitmap: Bitmap,displayName: String,mimeType: String,compressFormat: Bitmap.CompressFormat){
        val values = ContentValues()

        values.put(MediaStore.MediaColumns.DISPLAY_NAME,displayName)
        values.put(MediaStore.MediaColumns.MIME_TYPE,mimeType)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        } else {
            values.put(MediaStore.MediaColumns.DATA, "${Environment.getExternalStorageDirectory().path}/${Environment.DIRECTORY_DCIM}/$displayName")
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if(uri != null){
            val openOutputStream = contentResolver.openOutputStream(uri)
            if(openOutputStream != null){
                bitmap.compress(compressFormat,100,openOutputStream)
                openOutputStream.close()
            }
        }

    }


    //存储图片到沙盒
    fun saveImageToMine(bitmap: Bitmap){
        val fileName = "title.png"

        val externalFilesDir = getExternalFilesDir("Image/")
        val dirPath = "$externalFilesDir/"

        val file = File(dirPath)

        if(!file.exists()){
            if(!file.mkdir()){
                return
            }
        }

        val dirFile = File("$dirPath/$fileName")

        val fileOutputStream = FileOutputStream(dirFile)

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)
        val toByteArray = byteArrayOutputStream.toByteArray()

        fileOutputStream.write(toByteArray)
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    //从沙盒目录取出元素
    fun getImgFromMine(): ByteArray?{
        val externalFilesDir = getExternalFilesDir("Image/")
        val file = File(externalFilesDir.toString())
        val listFiles = file.listFiles()
        listFiles?.forEachIndexed { index, file ->
            val name = file.name
            //name 获取到的是 + 后缀的 例如 title.png
            if(file.isFile && "title.png" == name){
                val inputStream = FileInputStream(file)
                val byteArray = ByteArray(inputStream.available())
                inputStream.read(byteArray)
                inputStream.close()
                return byteArray
            }
        }
        return null
    }

}