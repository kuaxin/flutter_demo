package com.youjiaoyule.mvvmactual.base

import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.common.AppManager
import com.youjiaoyule.mvvmactual.utils.ActivityHook

/**
 *  @author RenGX on 2020/6/10
 *
 */
abstract class BaseActivity: AppCompatActivity() {
    private var mExitTime:Long = 0

    val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }

    //重新加载数据
    open fun reLoad() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        //防止Android 8.0 屏幕检测发生崩溃
        ActivityHook.hookOrientation(this)

        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ImmersionBar.with(this).fitsSystemWindows(true)
            .statusBarDarkFont(true).init()

        AppManager.addActivity(this)

        initView()
        initDate()
    }

    //获取到页面的布局
    abstract fun getLayoutId(): Int

    //初始化控件
    abstract fun initView()

    //加载数据
    abstract fun initDate()

    abstract fun onHandlerReceive(msg: Message)

    override fun onBackPressed() {
        if(AppManager.isLastActivity(this)){
            doExit()
        }else{
            AppManager.removeActivity(this)
        }
    }

    //退出App
    private fun doExit() {
        if(System.currentTimeMillis() - mExitTime > 2000){
            ToastUtils.show(resources.getString(R.string.exit_app))
            mExitTime = System.currentTimeMillis()
        }else {
            AppManager.exitApp(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.removeActivity(this)
    }
}