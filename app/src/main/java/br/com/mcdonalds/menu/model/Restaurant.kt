package br.com.mcdonalds.menu.model

data class Restaurant(
    val currency: String,
    val description: String,
    val id: String,
    val location: Location,
    val menus: List<Menu>,
    val name: String,
    val operationDays: List<OperationDay>,
    val phone: String,
    val privateParking: Boolean
)
