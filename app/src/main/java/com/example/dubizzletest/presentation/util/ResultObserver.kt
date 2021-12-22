package com.example.dubizzletest.presentation.util

import androidx.lifecycle.Observer
import com.example.dubizzletest.domain.common.Result

/**
 * @see ResultObserver is used to handle success, failure, loading at one place.
 */
class ResultObserver<T>(
    private val tag: String? = null,
    private val hideLoading: () -> Unit,
    private val showLoading: () -> Unit,
    private val onSuccess: (data: T) -> Unit,
    private val onError: (message: String) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(result: Result<T>?) {
        when (result) {
            is Result.Success -> {
                hideLoading()
                if (result.data != null) {
                    onSuccess(result.data)
                }
            }

            is Result.Error -> {
                hideLoading()
                onError(result.error)
            }

            is Result.Loading -> {
                showLoading()
            }
        }
    }
}
