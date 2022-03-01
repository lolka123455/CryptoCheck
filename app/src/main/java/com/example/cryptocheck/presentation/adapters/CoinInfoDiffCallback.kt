package com.example.cryptocheck.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocheck.domain.CoinInfo

/*This code is comparing the old and new items to see if they are the same. If they are, then we
don't need to update anything. If not, then we do need to update.*/
object CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}