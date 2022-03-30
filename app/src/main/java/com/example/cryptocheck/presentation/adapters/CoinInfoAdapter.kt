package com.example.cryptocheck.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptocheck.R
import com.example.cryptocheck.databinding.ItemCoinInfoBinding
import com.example.cryptocheck.data.network.model.CoinInfo
import com.squareup.picasso.Picasso

/*This class is creating a list of CoinInfo objects and binding them to the RecyclerView.
The code uses Picasso to load the imageUrl into the ImageView ivLogoCoin. When a user clicks on an
item, it calls OnCoinClickListener's onCoinClick method with that coin object as its parameter*/
class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoViewHolder>
    (CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    //This function is inflating the layout for each item in the recyclerview.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    //This fun is binding the data to the views.
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    /*This code is listening for a click on the coin image. When it detects that a click has been
    made, it will call the `onCoinClick` method in the OnCoinClickListener interface and pass along
    the CoinInfo object associated with this particular coin.*/
    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}