package com.books.application.common.widgets

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.pm.PackageManager
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import java.io.File


object AppUtils {

    fun isSDCardEnable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun getAppCachePath(context: Context): String {
        return if (isSDCardEnable() && context.externalCacheDir != null) {
            context.externalCacheDir!!.path
        } else {
            context.cacheDir.path
        }
    }

    fun getAppFilesPath(context: Context): String {
        return if (isSDCardEnable() && context.getExternalFilesDir("") != null) {
            context.getExternalFilesDir("")!!.path
        } else {
            context.filesDir.path
        }
    }

    fun deviceId(context: Context): String {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
    }

    fun versionName(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0)
        return packageInfo.versionName
    }

    fun hideSoftKeyboard(view: View, context: Context) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun getVersionName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }

    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return 0
    }

    fun clearAllCache(context: Context) {
        deleteFile(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteFile(context.externalCacheDir)
        }
    }

    fun deleteFile(file: File?): Boolean {
        if (file == null || !file.exists()) return false
        if (file.isFile) {
            val to = File(file.absolutePath + System.currentTimeMillis())
            file.renameTo(to)
            to.delete()
        } else {
            val files = file.listFiles()
            if (files != null && files.size > 0)
                for (innerFile in files) {
                    deleteFile(innerFile)
                }
            val to = File(file.absolutePath + System.currentTimeMillis())
            file.renameTo(to)
            return to.delete()
        }
        return false
    }

    fun isAppForeground(context: Context): Boolean {
        val manager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager?
        val appProcessInfos = manager!!.runningAppProcesses
        if (appProcessInfos == null || appProcessInfos.isEmpty()) {
            return false
        }
        for (info in appProcessInfos) {
            if (info.processName == context.packageName &&
                    info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true
            }
        }
        return false
    }

    fun setImmersiveMode(window: Window) {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }
}