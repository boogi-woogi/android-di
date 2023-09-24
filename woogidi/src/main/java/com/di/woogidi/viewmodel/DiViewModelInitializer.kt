package com.di.woogidi.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.boogiwoogi.di.InstanceContainer
import com.boogiwoogi.di.Module
import com.di.woogidi.activity.DiActivity
import com.di.woogidi.application.DiApplication

@MainThread
inline fun <reified VM : ViewModel> DiActivity.diViewModels(): Lazy<VM> = ViewModelLazy(
    VM::class,
    { viewModelStore },
    { diViewModelFactory<VM>(instanceContainer, module) },
)

inline fun <reified VM : ViewModel> diViewModelFactory(
    container: InstanceContainer,
    module: Module
): ViewModelProvider.Factory = viewModelFactory {
    initializer {
        DiApplication.run {
            injector.inject<VM>(container, module)
        }
    }
}