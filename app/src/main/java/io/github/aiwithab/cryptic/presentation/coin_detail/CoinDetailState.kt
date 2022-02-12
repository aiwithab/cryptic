package io.github.aiwithab.cryptic.presentation.coin_detail

import io.github.aiwithab.cryptic.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
