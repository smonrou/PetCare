package com.development.petcare.objects.providers

import com.development.petcare.objects.basics.ActiveUser

class UserProvider {
    companion object{
        var activeUserList = mutableListOf<ActiveUser>()
    }
}