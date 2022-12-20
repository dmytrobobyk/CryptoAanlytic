package com.example.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.DB_CRYPTOCURRENCY

@Entity(tableName = DB_CRYPTOCURRENCY)
data class DbCryptocurrencyDetails(
    @PrimaryKey(autoGenerate = false)
    val cryptocurrencyDetailsId: String,
    val categories: List<String>,
    val coingeckoRank: Int,
    val coingeckoScore: Double,
    val description: String,
    val genesisDate: String,
    val hashingAlgorithm: String,
    val lastUpdated: String,
    val marketCap_rank: Int,
    val name: String,
    val sentimentVotesDownPercentage: Double,
    val sentimentVotesUpPercentage: Double,
    val symbol: String,
    @Embedded
    val marketData: DbMarketData,
    @Embedded
    val links: DbLinks
    )