package com.belatrixsf.baseproject.models.database

import com.belatrixsf.baseproject.models.api.UserDto

data class User(
        var id: Long,
        var firstname: String,
        var lastname: String,
        var email: String,
        var password: String
) {
    companion object {
        fun fromDto(userDto: UserDto): User {
            return User(
                    id = userDto.id!!,
                    firstname = userDto.firstname,
                    lastname = userDto.lastname,
                    email = userDto.email,
                    password = userDto.password
            )
        }
    }
}