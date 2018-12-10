package com.belatrixsf.baseproject

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log
import com.belatrixsf.baseproject.extensions.isFatal
import com.belatrixsf.baseproject.extensions.isNonFatal
import com.orhanobut.hawk.Hawk
import com.squareup.leakcanary.LeakCanary
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

/**
 * Created by luis on 25/11/17.
 * Implements the application class
 */
class MvpLabKotlinApplication : Application() {
    companion object {
        lateinit var context: MvpLabKotlinApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initLeakCanary()
        initLogger()
        initSecureDatabase()
        initRxErrorHandler()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + "." + element.methodName + "(" + element.lineNumber + ")"
                }
            })
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun initSecureDatabase() = Hawk.init(context).build()

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { error ->
            when {
                error is UndeliverableException -> Timber.w(error.cause)
                error.isNonFatal() -> Timber.w(error)
                error.isFatal() -> Thread.currentThread().uncaughtExceptionHandler.uncaughtException(
                        Thread.currentThread(),
                        error
                )
            }
        }
    }

    /** A tree which logs important information for crash reporting. */
    @SuppressLint("LogNotTimber")
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return
            }

            if (priority == Log.ERROR && t != null) {
                //Crashlytics.logException(t)
            }

            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }

            var i = 0
            val length = message.length
            while (i < length) {
                var newLine = message.indexOf('\n', i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = Math.min(newLine, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < newLine)
                i++
            }
        }

        companion object {
            /** The max size of a line  */
            private const val MAX_LOG_LENGTH = 4000
        }
    }
}