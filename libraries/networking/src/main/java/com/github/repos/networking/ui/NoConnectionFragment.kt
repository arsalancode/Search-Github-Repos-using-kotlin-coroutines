package com.github.repos.networking.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.feliperrm.utils.getViewModel
import com.github.repos.core.FullScreenDialogFragment
import com.github.repos.networking.R
import com.github.repos.networking.databinding.FragmentNoConnectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_no_connection.*
import javax.inject.Inject

@AndroidEntryPoint
class NoConnectionFragment : FullScreenDialogFragment() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private val vm: NoConnectionViewModel by viewModels()

    private val hasPoorConnection by lazy {
        arguments?.getBoolean(POOR_CONNECTION_KEY) ?: false
    }

    private var onRetry: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentNoConnectionBinding.inflate(inflater, container, false).apply {
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivNoConnectionClose.setOnClickListener { dismiss() }

        if (hasPoorConnection) {
            tvNoConnectionTitle.text = getString(R.string.poor_connection_title)
        }

        vm.hasInternetConnection.observe(viewLifecycleOwner, Observer {
            if (it) {
                onRetry?.invoke()

                dismiss()
            }
        })
    }

    fun setOnRetryCallBack(onRetry: (() -> Unit)): NoConnectionFragment {
        this.onRetry = onRetry
        return this
    }

    companion object {
        private const val POOR_CONNECTION_KEY = "connectionErrorKey"
        fun newInstance(hasPoorConnection: Boolean = false): NoConnectionFragment =
            NoConnectionFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(POOR_CONNECTION_KEY, hasPoorConnection)
                }
            }
    }
}