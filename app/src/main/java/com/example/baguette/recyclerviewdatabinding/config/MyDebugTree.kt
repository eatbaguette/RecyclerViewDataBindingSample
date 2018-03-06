package com.example.baguette.recyclerviewdatabinding.config

import android.util.Log
import timber.log.Timber


class MyDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val thread = Throwable().stackTrace

        if (thread != null && thread.size >= 5) {
            val stack = thread[5]
            val className = stack.className
            val packageName = className.substring(0, className.lastIndexOf("."))
            val fileName = stack.fileName

            val msg = message + " at " + packageName + "(" + fileName + ":" + stack.lineNumber + ")"
            Log.println(priority, tag, msg)
        } else {
            Log.println(priority, tag, message)
        }
    }
}
