package com.di.woogidi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boogiwoogi.di.InstanceContainer
import com.boogiwoogi.di.Module
import com.di.woogidi.application.DiApplication

abstract class DiActivity : AppCompatActivity() {

    val instanceContainer: InstanceContainer = ActivityInstanceContainer()
    abstract val module: Module

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupInjector()
    }

    override fun onPause() {
        if (isFinishing) {
            instanceContainer.clear()
        }
        super.onPause()
    }

    private fun setupInjector() {
        DiApplication.injector.injectMemberProperty(
            activityInstanceContainer = instanceContainer,
            activityModule = module,
            target = this
        )
    }
}