package com.parserdev.store.data.utils

import com.parserdev.store.domain.network.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T,X> safeApiCall(apiToBeCalled: suspend () -> Response<T>, mapper: (T) -> X): NetworkResult<X> {

    return try {
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                NetworkResult.Success(data = mapper.invoke(response.body()!!))
            }
            else NetworkResult.Error(message = response.message() ?: SOMETHING_WENT_WRONG)

        } catch (e: HttpException) {
            NetworkResult.Error(message = e.message ?: SOMETHING_WENT_WRONG)
        } catch (e: IOException) {
            NetworkResult.Error(message = NO_CONNECTION)
        } catch (e: Exception) {
            NetworkResult.Error(message = SOMETHING_WENT_WRONG)
        }
}

const val SOMETHING_WENT_WRONG = "Something went wrong"
const val NO_CONNECTION = "Please check your network connection"