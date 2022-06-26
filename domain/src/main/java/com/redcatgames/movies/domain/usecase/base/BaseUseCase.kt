package com.redcatgames.movies.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class BaseUseCase(
    protected val main: CoroutineDispatcher = Dispatchers.Main,
    protected val io: CoroutineDispatcher = Dispatchers.IO
)