package com.github.repos.home.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feliperrm.utils.getViewModel
import com.github.repos.base.BaseFragment
import com.github.repos.home.HomeViewModel
import com.github.repos.home.R
import com.github.repos.home.databinding.HomeFragmentBinding
import com.github.repos.home.model.UiStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.net.SocketTimeoutException

@FlowPreview
@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val TAG = "HomeFragment"

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    private lateinit var recyclerRepoState: Parcelable

    @ExperimentalCoroutinesApi
    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = HomeFragmentBinding.inflate(inflater, container, false).apply {
        viewModel = vm
        lifecycleOwner = viewLifecycleOwner
        lfo = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerRepos.layoutManager = LinearLayoutManager(activity)
        linearLayoutManager = recyclerRepos.layoutManager as LinearLayoutManager
        recyclerRepoState = linearLayoutManager.onSaveInstanceState()!!

        setRecyclerViewScrollListener()

        vm.noConnectionEvent.observe(viewLifecycleOwner, {
            if (it.first) {
                noConnectionFragment(it.second is SocketTimeoutException)
                    .show(childFragmentManager, "")
            }
        })

        vm.uiStates.observe(viewLifecycleOwner, {

            if (it == UiStates.LOAD_MORE)
                recyclerRepoState = linearLayoutManager.onSaveInstanceState()!!

        })

        vm.repoUiModelList.observe(viewLifecycleOwner, {
            linearLayoutManager.onRestoreInstanceState(recyclerRepoState)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.search)
        val searchView: SearchView = MenuItemCompat.getActionView(search) as SearchView
        setupSearchView(searchView)
    }

    private fun setRecyclerViewScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    vm.loadMore()
                }
            }
        }

        recyclerRepos.addOnScrollListener(scrollListener)
    }

    private fun setupSearchView(searchView: SearchView) {

        searchView.queryHint = activity?.resources?.getString(R.string.search_query_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                vm.searchRepos(newText)
                return false
            }
        })

        searchView.setOnCloseListener {
            vm.uiStates.postValue(UiStates.WELCOME)
            false
        }

    }

}