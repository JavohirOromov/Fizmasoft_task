package com.javohir.fizmasofttask.domain.usecase

import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.model.PrayerTimes
import com.javohir.fizmasofttask.domain.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Use Case
 */
class GetPrayerTimesUseCase @Inject constructor(
    private val repository: PrayerRepository
) {

    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<PrayerTimes>>{
        return flow {
            emit(Resource.Loading)

            val cached = repository.getCached()
            if (cached != null) emit(Resource.Success(cached))

            try {
                val date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                val remote = repository.fetchRemote(latitude, longitude, date)
                repository.cache(remote)
                emit(Resource.Success(remote))
            } catch (e: Exception) {
                if (cached == null) emit(Resource.Error(e.message ?: "Tarmoq xatosi"))
            }
        }
    }
}