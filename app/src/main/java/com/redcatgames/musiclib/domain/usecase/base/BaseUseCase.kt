package com.redcatgames.musiclib.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class BaseUseCase(
    protected val io: CoroutineDispatcher = Dispatchers.IO
)