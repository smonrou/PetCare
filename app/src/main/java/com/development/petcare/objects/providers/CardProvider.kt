package com.development.petcare.objects.providers

import com.development.petcare.objects.basics.Card

class CardProvider {
    companion object{
        var CardList = mutableListOf(
            Card("Gastos Normales", "Saúl Monroy", "2472832662001", "101", "5/28", "Visa"),
            Card("Banrural", "Any Marroquín", "2472832662001", "102", "2/24","MasterCard"),
            Card("GyT", "Henry Monroy", "2472832662001", "103", "8/30","Visa"),
            Card("American Express", "Carlos Marroquín", "2472832662001", "104", "1/30","American Express")
        )
    }
}