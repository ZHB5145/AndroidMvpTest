package com.starvictory.common.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BasisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initWindows()
        if (intent.extras?.let { initArgs(it) } == true) {
            setContentView(getContentLayoutId())
            initWidget()
            initData()
        } else {
            finish()
        }
    }

    /**
     * 初始化窗口
     */

    protected fun initWindows() {}

    /**
     * 初始化参数 bundle
     * @return 如果正确返回true  错误返回false
     */

    protected abstract fun initArgs(bundle: Bundle): Boolean

    /**
     *得到当前界面资源文件ID
     * @return 资源文件Id
     */
    protected abstract fun getContentLayoutId(): Int

    /**
     * 初始化控件
     */
    protected fun initWidget() {

    }

    /**
     * 初始化数据
     */
    protected fun initData() {

    }

    override fun onSupportNavigateUp(): Boolean {
        //当点击界面导航返回时，finish当前界面
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        //获取fragment列表
        val childFragments: List<Fragment> = supportFragmentManager.fragments
        if (childFragments.isNotEmpty()) {
            for (fragment in childFragments) {
                if (fragment is BasisFragment) {
                    if (fragment.onBackPressed())
                        return
                }
            }
        }
        super.onBackPressed()
        finish()
    }
}