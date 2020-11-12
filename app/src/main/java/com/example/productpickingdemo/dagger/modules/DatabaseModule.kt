package com.example.productpickingdemo.dagger.modules

import android.app.Application
import androidx.room.Room
import com.example.productpickingdemo.database.AppDatabase
import com.example.productpickingdemo.database.AppDatabaseImpl
import com.example.productpickingdemo.database.AppRoomDataBase
import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(app: Application) {
    private val appRoomDataBase: AppRoomDataBase

    init {
        appRoomDataBase = Room.databaseBuilder(app, AppRoomDataBase::class.java, "demo-db").build()
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
/*

@Module
class RoomModule(mApplication: Application?) {
    private val demoDatabase: DemoDatabase
    init {
        demoDatabase =
            Room.databaseBuilder(mApplication!!, DemoDatabase::class.java, "demo-db").build()
    }


    @Singleton
    @Provides
    fun providesRoomDatabase(): DemoDatabase {
        return demoDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: DemoDatabase): ProductDao {
        return demoDatabase.getProductDao()
    }

    @Singleton
    @Provides
    fun productRepository(productDao: ProductDao?): ProductRepository {
        return ProductDataSource(productDao)
    }


}*/
