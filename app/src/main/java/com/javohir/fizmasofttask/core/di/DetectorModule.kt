package com.javohir.fizmasofttask.core.di

import com.javohir.fizmasofttask.core.detector.FaceDetector
import com.javohir.fizmasofttask.core.detector.MlKitFaceDetector
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.di
 * Description: Face detector Depency Injection
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DetectorModule{

    @Binds
    abstract fun bindFaceDetector(impl: MlKitFaceDetector): FaceDetector

}