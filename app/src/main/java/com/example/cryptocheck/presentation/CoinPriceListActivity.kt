package com.example.cryptocheck.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocheck.R
import com.example.cryptocheck.data.network.model.CoinInfo
import com.example.cryptocheck.databinding.ActivityCoinPriceListBinding
import com.example.cryptocheck.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

/*This class is observing the coinInfoList from CoinViewModel. It's using a custom adapter to
display the data in RecyclerView. The onCoinClickListener is used to handle click event of each item
in RecyclerView. When user clicks an item, it will launch DetailActivity or DetailFragment depending
on whether device has one pane mode or not.*/
class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }

        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.animation = null
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    /*This function is checking if the fragment container is null. If it's not, then we know that there
    are two fragments in this activity and therefore we're in one pane mode. If the fragment
    container is null, then we know that there's only one fragment in this activity and therefore
    we're in two pane mode.*/
    private fun isOnePaneMode() = binding.fragmentContainer == null

    //This function is launching the CoinDetailActivity with a symbol as an extra.
    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    /*This function is creating a new instance of the CoinDetailFragment and passing in the symbol
    as an argument*/
    private fun launchDetailFragment(fromSymbol: String) {
        val fragment = CoinDetailFragment.newInstance(fromSymbol)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}