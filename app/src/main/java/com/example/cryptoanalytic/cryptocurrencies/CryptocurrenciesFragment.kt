package com.example.cryptoanalytic.cryptocurrencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cryptoanalytic.R
import com.example.cryptoanalytic.cryptocurrencies.viewmodel.CryptocurrenciesViewModel
import com.example.cryptoanalytic.databinding.CryptocurrencyListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CryptocurrenciesFragment : Fragment() {

    @Inject
    lateinit var viewModel: CryptocurrenciesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: CryptocurrencyListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.cryptocurrency_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toolbar.toolbarTitleTextView.text = "Fragment time"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val a = viewModel.cryptocurrenciesList.replayCache

//        val navController = (childFragmentManager.findFragmentById(R.id.bottomNavigationContainer) as NavHostFragment).navController
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)


    }
}