package com.example.myapplication

data class Users(var name:String, var lastName:String, var age:String, var email:String){
    constructor(email:String) : this("", "", "", email) {
        this.name = name
        this.lastName = lastName
        this.age = age
        this.email = email
    }
}
