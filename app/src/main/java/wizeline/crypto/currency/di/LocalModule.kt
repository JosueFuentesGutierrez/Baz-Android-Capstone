package wizeline.crypto.currency.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wizeline.crypto.currency.data.resource.local.dao.AvailableBooksDao
import wizeline.crypto.currency.data.resource.local.dao.InformationTradingDao
import wizeline.crypto.currency.data.resource.local.dao.OrderBookDao
import wizeline.crypto.currency.data.resource.local.dataBase.CryptoDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providerDataBase(@ApplicationContext mContext:Context):CryptoDataBase =
        Room.databaseBuilder(
            mContext,
            CryptoDataBase::class.java,
            "wizeline.crypto.currency.crypto_database5"
        ).build()

    @Provides
    @Singleton
    fun provideCryptoDao(cryptoDataBase: CryptoDataBase):AvailableBooksDao=
        cryptoDataBase.availableBook()

    @Provides
    @Singleton
    fun provideTickerDao(cryptoDataBase: CryptoDataBase):InformationTradingDao=
        cryptoDataBase.informationTrading()

    @Provides
    @Singleton
    fun provideOrderDao(cryptoDataBase: CryptoDataBase):OrderBookDao=
        cryptoDataBase.orderBook()



}