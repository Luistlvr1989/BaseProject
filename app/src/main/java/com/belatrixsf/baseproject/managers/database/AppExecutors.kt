package com.belatrixsf.superpet.managers.database

import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors private constructor(
        private val diskIO: Executor,
        private val networkIO: Executor,
        private val mainThread: Executor
) {
    constructor() : this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), MainThreadExecutor()) {}

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(@NonNull command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}