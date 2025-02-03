package com.example.myapplication

import com.example.myapplication.data.User

object UserMapper {
    fun UserResponse.toUser(): User {
        return User(
            id = this.id,
            avatar = this.avatar?.toInt(),
            firstName = this.firstName,
            lastName = this.lastName,
            about = this.about,
            activationStatus = this.activeStatus

        )
    }
}