package com.example.cryptocheck.presentation

import com.example.cryptocheck.presentation.adapters.CoinInfoAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.R
import com.example.cryptocheck.data.model.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {


    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val rvCoinPriceList = findViewById<RecyclerView>(R.id.rvCoinPriceList)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }

        }
        rvCoinPriceList.adapter = adapter

        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(CoinViewModel::class.java)
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}