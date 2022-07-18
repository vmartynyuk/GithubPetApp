package ua.vmartyniuk.githubpetapp.presentation.utils

import android.content.Context
import androidx.paging.LoadState
import ua.vmartyniuk.githubpetapp.R
import ua.vmartyniuk.githubpetapp.data.network.exception.GithubPetApiErrorCodes
import ua.vmartyniuk.githubpetapp.data.network.exception.GithubPetApiException

internal fun LoadState.Error.getMessage(context: Context): String {
    val error = this.error
    return if (error is GithubPetApiException) {
        when (error.code) {
            GithubPetApiErrorCodes.CONNECTION_FAILURE -> context.getString(R.string.error_connection_text)
            GithubPetApiErrorCodes.EMPTY_BODY,
            GithubPetApiErrorCodes.UNKNOWN_ERROR -> context.getString(R.string.error_unknown)
            else -> error.message
        } ?: context.getString(R.string.error_unknown)
    } else context.getString(R.string.error_unknown)
}