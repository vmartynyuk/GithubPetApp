package ua.vmartyniuk.githubpetapp.data.network.exception

import java.io.IOException

open class GithubPetApiException(
    val code: Int,
    message: String?,
    cause: Throwable? = null
) : IOException(message, cause)

open class GithubPetConnectionException(
    message: String?,
    cause: Throwable? = null
) : GithubPetApiException(GithubPetApiErrorCodes.CONNECTION_FAILURE, message, cause)

object GithubPetApiErrorCodes {
    const val UNKNOWN_ERROR = -2
    const val EMPTY_BODY = -1
    const val CONNECTION_FAILURE = 0
}
