package com.example.productpickingdemo.dagger.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.productpickingdemo.database.AppDatabase
import com.example.productpickingdemo.database.AppDatabaseImpl
import com.example.productpickingdemo.database.AppRoomDataBase
import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
import com.example.productpickingdemo.database.shared_preferences.SharedPreferencesImpl
import com.example.productpickingdemo.database.shared_preferences.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val app: Application) {
    private val appRoomDataBase: AppRoomDataBase =
        Room.databaseBuilder(app, AppRoomDataBase::class.java, "demo-db").build()

    @Singleton
    @Provides
    fun provideSPref(): SharedPreferencesManager {
        return SharedPreferencesImpl(app.applicationContext)
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppRoomDataBase {
        return appRoomDataBase
    }

    @Singleton
    @Provides
    fun providesLocationDao(): LocationDao {
        return appRoomDataBase.locationDao()
    }

    @Singleton
    @Provides
    fun providesOrderDao(): OrderDao {
        return appRoomDataBase.orderDao()
    }

    @Singleton
    @Provides
    fun providesProductDao(): ProductDao {
        return appRoomDataBase.productDao()
    }

    @Singleton
    @Provides
    fun providesUserDao(): UserDao {
        return appRoomDataBase.userDao()
    }

    @Singleton
    @Provides
    fun appDatabase(
        locationDao: LocationDao,
        orderDao: OrderDao,
        userDao: UserDao,
        productDao: ProductDao
    ): AppDatabase {
        return AppDatabaseImpl(locationDao, orderDao, userDao, productDao)
    }
}