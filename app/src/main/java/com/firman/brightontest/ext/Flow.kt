package com.firman.brightontest.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(
    owner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit = {},
): Job = owner.lifecycleScope.launch {
    owner.lifecycle.repeatOnLifecycle(state = lifecycleState) {
        collect(action)
    }
}


fun <T> LifecycleOwner.execute(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit = {},
): Job = lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(state = lifecycleState) {
        flow.collect(action)
    }
}