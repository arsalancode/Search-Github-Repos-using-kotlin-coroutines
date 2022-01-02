package com.github.repos.networking.networking.repo

import com.github.repos.networking.networking.repo.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {

    companion object {
        const val PER_PAGE_LIMIT = 30
        const val SEARCH_REPOS_PATH = "search/repositories"
    }

    /**
     *
     *  Method to query repos using Github Search API
     *  More details on: https://docs.github.com/en/rest/reference/search#search-repositories
     *  Example API call: https://api.github.com/search/repositories?q=a&page=1&per_page=30
     */

    @GET(SEARCH_REPOS_PATH)
    suspend fun searchRepos(
        @Query("q") searchTerm: String,
        @Query("page") pageNo: Int = 1, // default = 1
        @Query("per_page") perPage: Int = PER_PAGE_LIMIT
    ): SearchResult

}