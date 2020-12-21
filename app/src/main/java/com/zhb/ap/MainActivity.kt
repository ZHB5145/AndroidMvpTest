package com.zhb.ap

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private lateinit var button:Button
//    private lateinit var button2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    fun initView(){
//        button =findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
//        button.setOnClickListener {
//
//        }
//        button2.setOnClickListener(View.OnClickListener {  })
        wv_test.settings.javaScriptEnabled = true;
        wv_test.settings.domStorageEnabled = true
        val url = "https://support.qq.com/product/299618"
        val webViewClient:WebViewClient = myWebViewClient()
        wv_test.webViewClient=webViewClient
        wv_test.loadUrl(url)
    }
    class myWebViewClient:WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

             super.shouldOverrideUrlLoading(view, request)
            if (request != null) {
                view?.loadUrl(request.url.toString())
            }
            return true
        }
    }

}