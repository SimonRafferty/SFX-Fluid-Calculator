package com.sfx.fluidcalc

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    // Keep a reference for printing (must not be garbage collected)
    private var printWebView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        setupWebView()

        // Check for internet connection (needed for Tailwind CSS CDN)
        if (!isNetworkAvailable()) {
            showNoInternetDialog()
        }

        // Add JavaScript interface for printing
        webView.addJavascriptInterface(WebAppInterface(), "AndroidPrint")

        // Load the calculator from assets
        webView.loadUrl("file:///android_asset/SFX_Fluid_Calculator.html")
    }

    // JavaScript interface for print functionality
    inner class WebAppInterface {
        @JavascriptInterface
        fun print() {
            runOnUiThread {
                printDocument()
            }
        }
    }

    // Print the current WebView content
    private fun printDocument() {
        val printManager = getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName = "${getString(R.string.app_name)} Report"

        // Prepare the print view by calling JavaScript
        webView.evaluateJavascript("if(typeof preparePrintView === 'function') preparePrintView();", null)

        // Create print adapter from the WebView
        val printAdapter = webView.createPrintDocumentAdapter(jobName)

        // Set print attributes
        val printAttributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()

        // Start the print job
        printManager.print(jobName, printAdapter, printAttributes)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.settings.apply {
            // Enable JavaScript (required for the calculator)
            javaScriptEnabled = true

            // Enable DOM storage for any local storage needs
            domStorageEnabled = true

            // Allow file access from file URLs
            allowFileAccess = true
            allowContentAccess = true

            // Enable zooming
            builtInZoomControls = true
            displayZoomControls = false

            // Set text zoom to 100%
            textZoom = 100

            // Enable viewport meta tag support
            useWideViewPort = true
            loadWithOverviewMode = true

            // Cache settings
            cacheMode = WebSettings.LOAD_DEFAULT

            // Allow mixed content (http resources from https pages)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Handle page loading progress
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }

        // Handle URL loading
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // Allow CDN requests for Tailwind CSS
                val url = request?.url?.toString() ?: return false
                if (url.startsWith("file://") ||
                    url.contains("cdn.tailwindcss.com") ||
                    url.contains("cdn.jsdelivr.net")) {
                    return false // Let WebView handle it
                }
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("This app requires an internet connection on first load to download styling. Some features may not display correctly.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Retry") { _, _ ->
                webView.reload()
            }
            .show()
    }

    // Handle back button to navigate within WebView
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }

    // Save WebView state on configuration changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }
}
