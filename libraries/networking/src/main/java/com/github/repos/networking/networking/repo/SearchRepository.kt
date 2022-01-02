package com.github.repos.networking.networking.repo

import com.github.repos.networking.networking.repo.model.SearchResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    @FlowPreview
    suspend fun searchGitRepos(query: String, pageNo: Int): Flow<SearchResult>
}
