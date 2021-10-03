package com.example.dietmemory.config

interface BasePresenter<T> {
    fun takeView(inputView : T)
    fun dropView()
}