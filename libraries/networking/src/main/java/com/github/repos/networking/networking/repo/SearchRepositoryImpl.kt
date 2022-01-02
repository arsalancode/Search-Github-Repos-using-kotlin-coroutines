package com.github.repos.networking.networking.repo

import com.github.repos.core.utils.flow.asFlow
import com.github.repos.networking.domain.InternetHelper
import com.github.repos.networking.networking.repo.model.RepoModel
import com.github.repos.networking.networking.repo.model.SearchResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class SearchRepositoryImpl @Inject constructor (
    val internetHelper: InternetHelper,
    val service: RepoService
) : SearchRepository {

    @FlowPreview
    override suspend fun searchGitRepos(query: String, pageNo: Int): Flow<SearchResult> =
        internetHelper.executeCheckInternetStatus()
            .flatMapConcat { asFlow { service.searchRepos(query, pageNo) } }
            .map { it }


}