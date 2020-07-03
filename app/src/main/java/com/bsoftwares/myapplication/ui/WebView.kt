package com.bsoftwares.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bsoftwares.myapplication.R
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(arguments?.getString("url"))
    }

}