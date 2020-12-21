package com.starvictory.common.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BasisFragment : Fragment() {
    protected var mRootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            val layoutId = getContentLayoutId()
            val root = inflater.inflate(layoutId, container, false)
            mRootView = root
            initWidget(root)
        } else {
            if (mRootView!!.parent != null) {
                //把当前rootview从其父控件中移出
                (mRootView!!.parent as ViewGroup).removeView(mRootView)
            }
        }

        return mRootView

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //初始化参数
        arguments?.let { initArgs(it) }
    }

    /**
     * 初始化参数 bundle
     * @return 如果正确返回true  错误返回false
     */

    protected fun initArgs(bundle: Bundle) {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //当view创建完成后初始化数据
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     *得到当前界面资源文件ID
     * @return 资源文件Id
     */
    protected abstract fun getContentLayoutId(): Int

    /**
     * 初始化控件
     */
    protected fun initWidget(root: View) {

    }

    /**
     * 初始化数据
     */
    protected fun initData() {

    }

    /**
     * 返回按键触发时调用
     * @return 返回TRUE fragment拦截返回逻辑    返回false  fragment不拦截返回逻辑
     */

    fun onBackPressed(): Boolean {

        return false;
    }

}