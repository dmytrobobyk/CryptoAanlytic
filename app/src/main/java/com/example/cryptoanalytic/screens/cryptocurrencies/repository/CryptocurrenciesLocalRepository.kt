package com.example.cryptoanalytic.screens.cryptocurrencies.repository

import com.example.cryptoanalytic.common.Result
import com.example.cryptoanalytic.screens.cryptocurrencies.datasource.CryptocurrenciesRemoteDataSource
import com.example.database.AppDatabase
import com.example.database.DaoAggregator
import com.example.database.embeeded.Cryptocurrency
import com.example.database.entity.DbCryptocurrency
import com.example.database.entity.DbRoi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptocurrenciesLocalRepository @Inject constructor(
    private val remoteDataSource: CryptocurrenciesRemoteDataSource,
    private val daoAggregator: DaoAggregator
) : CryptocurrenciesRepository {

    //    override suspend fun getLatestCryptocurrencies(): Flow<Result<List<CryptocurrencyResponseItem>>> {
    override suspend fun getLatestCryptocurrencies(): Flow<Result<List<Cryptocurrency>>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(daoAggregator.getCryptocurrencyList()))

            val result = remoteDataSource.getLatestCryptocurrencies()
            if (result is Result.Success) {
                val mappedList = result.data?.let {
                    it.map { responseItem ->
                        with(responseItem) {
                            Cryptocurrency(
                                DbCryptocurrency(id, ath, athChangePercentage, athDate, atl, atlChangePercentage, atlDate,
                                circulatingSupply, currentPrice, fullyDilutedValuation, high24h, image, lastUpdated,
                                low24h, marketCap, marketCapChange24h, marketCapChangePercentage24h, marketCapRank, maxSupply,
                                name, priceChange24h, priceChangePercentage24h, symbol, totalSupply, totalVolume),
                                DbRoi(id, roiResponse.currency, roiResponse.percentage, roiResponse.times)
                            )
                        }
                    }
                }?.let {
                    daoAggregator.deleteCryptocurrencyList(it)
                    daoAggregator.saveCryptocurrencyList(it)
                }
//                Result.Success(mappedList)
                Result.Success(daoAggregator.getCryptocurrencyList())
            }
            emit(Result.Finish)
        }
    }
}