package com.books.application.common.widgets

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.books.application.R

class Loading private constructor(var mContext: Context) {

    private var loadingDialog: LoadingDialog = this.LoadingDialog(mContext)

    companion object {
        fun build(context: Context): Loading {
            return Loading(context)
        }
    }

    fun show(show: Boolean) {
        if (show) {
            loadingDialog.show()
        } else {
            loadingDialog.dismiss()
        }
    }

    fun isLoading(): Boolean {
        return loadingDialog.isShowing
    }

    fun setOnDismissListener(listener: ((dialogInterface: DialogInterface) -> Unit)?) {
        loadingDialog.setOnDismissListener(listener)
    }

    private inner class LoadingDialog constructor(context: Context) : Dialog(context, R.style.LoadingDialogTheme) {

        init {
            this.setCanceledOnTouchOutside(false)
            val view = layoutInflater.inflate(R.layout.layout_loading, null)
            val progress = view.findViewById<ProgressBar>(R.id.progress)
            this.setContentView(view)
        }

        override fun onStart() {
            super.onStart()
            AppUtils.setImmersiveMode(window)
        }

        override fun onWindowFocusChanged(hasFocus: Boolean) {
            super.onWindowFocusChanged(hasFocus)
            AppUtils.setImmersiveMode(window)
        }

        override fun show() {
            // Set the dialog to not focusable.
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            if (isShowing) return
            if (!(mContext as AppCompatActivity).isFinishing) {
                super.show()
            }
            // Set the dialog to focusable again.
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        }

        override fun dismiss() {
            super.dismiss()
        }
    }
}