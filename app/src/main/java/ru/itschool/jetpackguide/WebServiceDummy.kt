package ru.itschool.jetpackguide

import javax.inject.Inject

class WebServiceDummy @Inject constructor(

) {
    val userMap: Map<String, User> = mapOf(
        "1" to User("1","Eldar Kurbanov", 22),
        "2" to User("2","Lev Tolstoy", 999),
        "3" to User("3","Lorem ipsum", 199999)
    )

    fun getUser(userID: String) : User? = userMap[userID]
}