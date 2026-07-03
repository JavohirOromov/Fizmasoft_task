package com.javohir.fizmasofttask.presentation.main.prayer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.core.notification.PrayerNotificationScheduler
import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.model.PrayerTime
import com.javohir.fizmasofttask.domain.usecase.GetCurrentLocationUseCase
import com.javohir.fizmasofttask.domain.usecase.GetPrayerTimesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: ViewModel
 */
@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val getCurrentLocation: GetCurrentLocationUseCase,
    private val getPrayerTimes: GetPrayerTimesUseCase,
    private val scheduler: PrayerNotificationScheduler,
) : ViewModel() {

    private val _state = MutableStateFlow(PrayerState())
    val state: StateFlow<PrayerState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<PrayerEvent>()
    val event = _event.asSharedFlow()

    private var tickerJob: Job? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(intent: PrayerIntent) {
        when (intent) {
            is PrayerIntent.PermissionResult -> {
                _state.update { it.copy(hasLocationPermission = intent.granted) }
                load()
            }
            is PrayerIntent.Refresh -> load()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun load() {
        viewModelScope.launch {
            val loc = getCurrentLocation()
            getPrayerTimes(loc.latitude, loc.longitude).collect { resource ->
                when (resource) {
                    is Resource.Loading ->
                        _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, prayerTimes = resource.data) }
                        scheduler.schedule(resource.data.times)
                        startTicker()
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _event.emit(PrayerEvent.ShowError(resource.message))
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startTicker() {
        tickerJob?.cancel()
        tickerJob = viewModelScope.launch {
            while (true) {
                val times = _state.value.prayerTimes?.times ?: break
                val next = computeNext(times)
                _state.update { it.copy(nextPrayerName = next?.first, countdown = next?.second) }
                delay(60_000.milliseconds)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun computeNext(times: List<PrayerTime>): Pair<String, String>? {
        val parsed = times.mapNotNull { p ->
            runCatching { LocalTime.parse(p.time) }.getOrNull()?.let { p.name to it }
        }
        if (parsed.isEmpty()) return null

        val now = LocalTime.now()
        val upcoming = parsed.firstOrNull { it.second.isAfter(now) }
        val (name, time, tomorrow) = if (upcoming != null) {
            Triple(upcoming.first, upcoming.second, false)
        } else {
            Triple(parsed.first().first, parsed.first().second, true)
        }

        val diffSec = time.toSecondOfDay() + (if (tomorrow) 24 * 3600 else 0) - now.toSecondOfDay()
        val hours = diffSec / 3600
        val minutes = (diffSec % 3600) / 60
        return name to "$hours soat $minutes daqiqa"
    }

    override fun onCleared() {
        tickerJob?.cancel()
    }
}
