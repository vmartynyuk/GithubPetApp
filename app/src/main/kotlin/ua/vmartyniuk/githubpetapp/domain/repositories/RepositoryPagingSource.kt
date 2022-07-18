package ua.vmartyniuk.githubpetapp.domain.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.vmartyniuk.githubpetapp.data.network.service.ApiService
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryFilter
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.domain.models.asModelList

class RepositoryPagingSource(
    private val apiService: ApiService,
    private val filter: RepositoryFilter
) : PagingSource<Int, RepositoryModel>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val response = apiService.getRepositories(
            filter.query,
            filter.sortBy.value,
            filter.order.value,
            page,
            params.loadSize
        )
        return if (response.isSuccess) {
            val repositories = response.getOrNull()?.asModelList ?: emptyList()
            val count = response.getOrNull()?.items?.size ?: 0
            val nextKey = if (count < params.loadSize) null else page + 1
            LoadResult.Page(
                data = repositories,
                prevKey = null,
                nextKey = nextKey
            )
        } else {
            LoadResult.Error(response.exceptionOrNull() ?: RuntimeException("Unknown Error"))
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}