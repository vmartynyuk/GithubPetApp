package ua.vmartyniuk.githubpetapp.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun LifecycleOwner.launchWithRepeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

internal fun Fragment.launchWithRepeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.launchWithRepeatOnStarted(block)
}