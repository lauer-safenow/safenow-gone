package de.safenow.port.input.employee

interface GetUsecase<T> {

    fun get(id: Int): T?
    fun getAll(): List<T>

}