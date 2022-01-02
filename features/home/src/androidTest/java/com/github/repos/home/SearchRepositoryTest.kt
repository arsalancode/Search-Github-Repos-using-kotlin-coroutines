package com.github.repos.home

import android.content.Context
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.github.repos.networking.domain.InternetHelper
import com.github.repos.networking.networking.repo.RepoService
import com.github.repos.networking.networking.repo.SearchRepository
import com.github.repos.networking.networking.repo.SearchRepositoryImpl
import com.github.repos.networking.networking.repo.model.Owner
import com.github.repos.networking.networking.repo.model.RepoModel
import com.github.repos.networking.provider.RetrofitBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.ArrayList

@Suppress("IllegalIdentifier")
@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class SearchRepositoryTest {
    private val mockWebServer = MockWebServer()

    lateinit var connectivityManager: ConnectivityManager
    lateinit var internetHelper: InternetHelper
    lateinit var retrofitInstance : RepoService
    lateinit var searchRepo: SearchRepository

    @Before
    fun initialise(){
        connectivityManager = getApplicationContext<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        internetHelper = InternetHelper(connectivityManager)
        retrofitInstance = RetrofitBuilder().githubInstance.create(RepoService::class.java)
        searchRepo = SearchRepositoryImpl(internetHelper, retrofitInstance)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Test
    fun fetchGitRepos_VerifyItemsSize() = runTest {

        mockWebServer.start()
        val expected = 30

        searchRepo.searchGitRepos("test", 1)
            .collect {
            assertEquals(expected, it.items.size)
        }
    }


    private fun getDummyRepos(): ArrayList<RepoModel> {
        val repoList = ArrayList<RepoModel>()
        for (i in 0..29) {

            val repo = RepoModel(
                Owner(
                    ownerAvatar = "https://avatars.githubusercontent.com/u/3281689?v=4",
                    ownerName = "Code SkyBlue"
                ),
                repoName = "gohttpserver",
                repoTitle ="gohttpserver",
                repoDesc = "The best HTTP Static File Server, write with golang+vue",
                repoUrl = "https://github.com/codeskyblue/gohttpserver"
            )

            repoList.add(repo)
        }

        return repoList
    }


}
