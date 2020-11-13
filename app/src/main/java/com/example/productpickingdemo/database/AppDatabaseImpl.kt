package com.example.productpickingdemo.database

import androidx.lifecycle.LiveData
import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.database.entities.User
import javax.inject.Inject

class AppDatabaseImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val orderDao: OrderDao,
    private val userDao: UserDao,
    private val productDao: ProductDao
) : AppDatabase {

    override suspend fun initDatabase() {
        userDao.clearData()
        orderDao.clearData()
        locationDao.clearData()
        productDao.clearData()

        userDao.insertAll(users)
        orderDao.insertAll(orders)
        locationDao.insertAll(locations)
        productDao.insertAll(products)
    }

    override suspend fun getOrders(): List<Order> {
        return orderDao.getAll()
    }

    override fun getProducts(orderId: Int): LiveData<List<Product>> {
        return productDao.getById(orderId)
    }

    override suspend fun getLocation(id: Int): Location {
        return locationDao.getById(id)
    }

    override suspend fun updateProduct(product: Product) {
        productDao.update(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDao.delete(product)
    }

    override suspend fun deleteOrder(order: Order) {
        orderDao.delete(order)
    }

    override fun getUser(id: Int): LiveData<User> {
        return userDao.getById(id)
    }

    override fun getUsers(): LiveData<List<User>> {
        return userDao.getAll()
    }

    override suspend fun printDb() {
        val users = userDao.getAll()
        val orders = orderDao.getAll()
        val product = productDao.getAll()
        val location = locationDao.getAll()
    }


    private val locations = listOf(
        Location(1, "R1", "5060773208282", "R1C1", "5060773208329", "R1C1S1", "5060773208367"),
        Location(2, "R2", "5060773208299", "R2C1", "5060773208336", "R2C1S2", "5060773208374"),
        Location(3, "R1", "5060773208282", "R1C2", "5060773208343", "R1C2S2", "5060773208381"),
        Location(4, "R2", "5060773208299", "R2C2", "5060773208350", "R1C2S1", "5060773208398")
    )

    private val orders = listOf(
        Order(1, 20201112005, "2020-11-12", "James_Brown"),
        Order(2, 20201112006, "2020-11-12", "Michael_Smith"),
        Order(3, 20201112007, "2020-11-12", "John_Davies")
    )

    private val products = listOf(
        Product(1, 1, 3, "Pens", "5060773208237", 21, 0),
        Product(2, 2, 2, "Paper", "5060773208244", 2, 0),
        Product(3, 2, 1, "Scissors", "5060773208251", 3, 0),
        Product(4, 3, 3, "Staplers", "5060773208268", 2, 0),
        Product(5, 3, 4, "Pens", "5060773208275", 10, 0)
    )

    private val users = listOf(
        User(1, "test1", "5060111111118", "12345678"),
        User(2, "test2", "5060222222228", "11111111"),
        User(3, "test3", "5060222222228", "123")
    )
}