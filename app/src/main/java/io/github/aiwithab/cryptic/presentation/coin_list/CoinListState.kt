package io.github.aiwithab.cryptic.presentation.coin_list

import io.github.aiwithab.cryptic.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
