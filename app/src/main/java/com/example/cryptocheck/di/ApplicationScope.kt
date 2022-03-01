package com.example.cryptocheck.di

import javax.inject.Scope

/*This code is creating a new annotation called ApplicationScope. This annotation will be used to
annotate the class that we want to use in our application.*/
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
