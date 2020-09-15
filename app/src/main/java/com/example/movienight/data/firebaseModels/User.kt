package com.example.movienight.data.firebaseModels

data class User(val uid: String?, val userName: String?) {
    constructor() : this(null, null)
}