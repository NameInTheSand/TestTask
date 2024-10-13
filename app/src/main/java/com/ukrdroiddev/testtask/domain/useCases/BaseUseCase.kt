package com.ukrdroiddev.testtask.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, out Type> where Type : Any {

    suspend operator fun invoke(params: Params): Result<Type> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(execute(params))
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    }

    protected abstract suspend fun execute(params: Params): Type
}