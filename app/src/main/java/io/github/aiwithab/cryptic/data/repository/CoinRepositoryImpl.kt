package io.github.aiwithab.cryptic.data.repository

import io.github.aiwithab.cryptic.data.remote.CoinPaprikaApi
import io.github.aiwithab.cryptic.data.remote.dto.CoinDetailDto
import io.github.aiwithab.cryptic.data.remote.dto.CoinDto
import io.github.aiwithab.cryptic.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}