package io.github.aiwithab.cryptic.domain.repository

import io.github.aiwithab.cryptic.data.remote.dto.CoinDetailDto
import io.github.aiwithab.cryptic.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}