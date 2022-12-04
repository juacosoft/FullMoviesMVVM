package com.juacodev.fullmovies.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class UnitTest {
    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@UnitTest)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}