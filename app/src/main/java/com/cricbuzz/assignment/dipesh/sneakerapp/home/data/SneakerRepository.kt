package com.cricbuzz.assignment.dipesh.sneakerapp.home.data

import android.content.Context
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.Media
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem
import com.cricbuzz.assignment.dipesh.sneakerapp.roomdb.AppDatabase
import com.cricbuzz.assignment.dipesh.sneakerapp.roomdb.CartItem

class SneakerRepository {


    suspend fun addItemToCart(context: Context, item: SneakerItem): Long {
        return AppDatabase.getDatabase(context).cartDao().insert(
            users = CartItem(
                sneakerId = item.id,
                size = "",
                title = item.title,
                colorWay = item.colorway,
                price = item.retailPrice
            )
        )

    }

    suspend fun loadSneakerById(id: String): SneakerItem = getAllSneakers().first { it.id == id }

    suspend fun getAllCartItems(context: Context): List<CartItem> {
        return AppDatabase.getDatabase(context).cartDao().getAll()
    }

    suspend fun removeItemFromCart(context: Context, item: CartItem) {
        return AppDatabase.getDatabase(context).cartDao().delete(item)
    }

    fun getAllSneakers(): ArrayList<SneakerItem> {
        return arrayListOf(
            SneakerItem(
                "Nike",
                "",
                "M",
                "1-st-bd-bhlflf",
                Media(
                    "https://freepngimg.com/thumb/shoes/27428-5-nike-shoes-transparent-background.png",
                    "https://freepngimg.com/thumb/shoes/27428-5-nike-shoes-transparent-background.png",
                    "https://freepngimg.com/thumb/shoes/27428-5-nike-shoes-transparent-background.png"
                ),
                "Nike Air Max pro 2021",
                "",
                199,
                "",
                "",
                "Nike Air Max pro 2021",
                2019
            ), SneakerItem(
                "Puma",
                "",
                "M",
                "2-st-bd-bhlflf",
                Media(
                    "https://www.pngfind.com/pngs/m/146-1460480_color-brilliancy-mens-puma-benny-black-white-textile.png",
                    "https://www.pngfind.com/pngs/m/146-1460480_color-brilliancy-mens-puma-benny-black-white-textile.png",
                    "https://www.pngfind.com/pngs/m/146-1460480_color-brilliancy-mens-puma-benny-black-white-textile.png"
                ),
                "Puma Air Max",
                "",
                399,
                "",
                "",
                "Puma Air max 2023",
                2022
            ), SneakerItem(
                "Gucci",
                "",
                "M",
                "3-st-bd-bhlflf",
                Media("", "", ""),
                "Gucci Max",
                "",
                1099,
                "",
                "",
                "Gucci Uni Mac",
                2019
            ), SneakerItem(
                "Adidas",
                "",
                "M",
                "4-st-bd-bhlflf",
                Media("", "", ""),
                "Adidas Air Max",
                "",
                699,
                "",
                "",
                "Adidas",
                2018
            ), SneakerItem(
                "Air Jordan",
                "",
                "M",
                "5-st-bd-bhlflf",
                Media("", "", ""),
                "Jordan Air Max",
                "",
                1909,
                "",
                "",
                "Air Jordan",
                2019
            ), SneakerItem(
                "Spikes",
                "",
                "M",
                "6-st-bd-bhlflf",
                Media("", "", ""),
                "Spikes Air Max $",
                "",
                109,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Lotto",
                "",
                "M",
                "7-st-bd-bhlflf",
                Media("", "", ""),
                "Lotto Air Max",
                "",
                499,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Puma",
                "",
                "M",
                "8-st-bd-bhlflf",
                Media("", "", ""),
                "Puma Air Max",
                "",
                159,
                "",
                "",
                "Puma",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "9-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Rebook",
                "",
                "M",
                "10-st-bd-bhlflf",
                Media("", "", ""),
                "Rebook (3) Max",
                "",
                699,
                "",
                "",
                "Rebook",
                2019
            ), SneakerItem(
                "Woodland",
                "",
                "M",
                "11-st-bd-bhlflf",
                Media("", "", ""),
                "Woodlan0 Air Max",
                "",
                199,
                "",
                "",
                "Woodland Vin",
                2019
            ), SneakerItem(
                "Metro",
                "",
                "M",
                "12-st-bd-bhlflf",
                Media("", "", ""),
                "Metro Air Flex",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Liberty",
                "",
                "M",
                "13-st-bd-bhlflf",
                Media("", "", ""),
                "Liberty Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Spectar",
                "",
                "M",
                "14-st-bd-bhlflf",
                Media("", "", ""),
                "Spectator Air Max",
                "",
                499,
                "",
                "",
                "Spectre",
                2019
            ), SneakerItem(
                "Bata",
                "",
                "M",
                "15-st-bd-bhlflf",
                Media("", "", ""),
                "Bata AirVc Max",
                "",
                699,
                "",
                "",
                "Bata",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "16-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Adidas",
                "",
                "M",
                "17-st-bd-bhlflf",
                Media("", "", ""),
                "Adidas Air Max",
                "",
                199,
                "",
                "",
                "Adidas",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "18-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "19-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "20-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "21-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "22-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "23-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "24-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "25-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "26-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "27-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "28-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "29-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "30-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "31-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            ), SneakerItem(
                "Nike",
                "",
                "M",
                "32-st-bd-bhlflf",
                Media("", "", ""),
                "Nike Air Max",
                "",
                199,
                "",
                "",
                "Nike",
                2019
            )
        )
    }
}