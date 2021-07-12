package com.mrz.irankart

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity: AppCompatActivity() {
    private var progress: ProgressDialog? = null
    var isConnect:Boolean=false
    /**
     * Show loading.
     */
    open fun showLoading() {
        hideLoading()
        progress = showLoadingDialog(this)
    }

    /**
     * Hide loading.
     */
    open fun hideLoading() {
        if (progress != null && progress!!.isShowing()) {
            progress!!.cancel()
        }
    }

    open fun showLoadingDialog(context: Context?): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    abstract fun observe()

    override fun onResume() {
        super.onResume()
        isNetworkConnected()
    }
    fun isNetworkConnected():Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
    }

}