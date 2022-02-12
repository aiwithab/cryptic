package io.github.aiwithab.cryptic.domain.use_cases.get_coin

import io.github.aiwithab.cryptic.common.Resource
import io.github.aiwithab.cryptic.data.remote.dto.toCoin
import io.github.aiwithab.cryptic.data.remote.dto.toCoinDetail
import io.github.aiwithab.cryptic.domain.model.Coin
import io.github.aiwithab.cryptic.domain.model.CoinDetail
import io.github.aiwithab.cryptic.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}