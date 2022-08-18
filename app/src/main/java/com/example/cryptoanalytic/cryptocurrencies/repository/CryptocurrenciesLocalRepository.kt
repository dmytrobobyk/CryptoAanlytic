package com.example.cryptoanalytic.cryptocurrencies.repository

import com.example.cryptoanalytic.common.Result
import com.example.cryptoanalytic.common.responses.latest.LatestResponse
import com.example.cryptoanalytic.cryptocurrencies.datasource.CryptocurrenciesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptocurrenciesLocalRepository @Inject constructor(private val dataSource: CryptocurrenciesDataSource): CryptocurrenciesRepository {

    override suspend fun getLatestCryptocurrencies(): Flow<Result<List<LatestResponse>>> {
        return flow {
            emit(Result.Loading)
            emit(dataSource.getLatestCryptocurrencies())
//            emit(Result.Success(LatestResponse(1, 1, "Piston",null, null, null,null, null, null,null, null, null,null, null, null,null)))
            emit(Result.Finish)
        }
    }
}