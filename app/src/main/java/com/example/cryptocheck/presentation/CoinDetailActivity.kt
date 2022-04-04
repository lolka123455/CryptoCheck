package com.example.cryptocheck.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocheck.R
import com.example.cryptocheck.databinding.ActivityCoinDetailBinding

/*This class is setting up the activity and inflating the layout. It's also checking if there is an
extra in the intent, if not it will finish this activity. If there is an extra, then we are creating
a new instance of CoinDetailFragment with the fromSymbol as a parameter to be passed into that fragment.*/
class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        /*This code is checking if the savedInstanceState is null. If it's not, then we know that
        this activity was recreated after a configuration change (e.g., screen rotation). In this
        case, the fragments will already be there and we don't need to add them again.*/
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
                .commit()
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}