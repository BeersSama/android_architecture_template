package com.example.android_architecture_template.presentation.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_architecture_template.R
import com.example.android_architecture_template.domain.base.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val lifecycleEventsDelegate =
        lazy { BehaviorSubject.createDefault(ViewModelEvent.CREATED) }

    private val lifecycleEvents by lifecycleEventsDelegate

    @Inject
    lateinit var resources: Resources

    private val _failure: Channel<Failure> = Channel(Channel.BUFFERED)
    val failure: Flow<Failure> = _failure.receiveAsFlow()

    override fun onCleared() {
        if (lifecycleEventsDelegate.isInitialized()) {
            lifecycleEvents.onNext(ViewModelEvent.CLEARED)
        }
        super.onCleared()
    }

    fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = when (throwable) {
            is Failure.NoInternet -> {
                Failure.NoInternet(resources.getString(R.string.error_no_internet))
            }
            is Failure.Api -> {
                Failure.Api(throwable.msg)
            }
            is Failure.Timeout -> {
                Failure.Timeout(resources.getString(R.string.error_timeout))
            }
            is Failure.Unknown -> {
                Failure.Unknown(resources.getString(R.string.error_unknown))
            }
            else -> {
                Failure.Unknown(resources.getString(R.string.error_unknown))
            }
        }

        failure.retryAction = retryAction
        viewModelScope.launch {
            _failure.send(failure)
        }
    }

    enum class ViewModelEvent {
        CREATED, CLEARED
    }
}