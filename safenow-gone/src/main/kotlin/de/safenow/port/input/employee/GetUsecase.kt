package de.safenow.port.input.employee

interface GetUsecase<ID, TYPE> {

    fun get(id: ID): TYPE?
    fun getAll(): List<TYPE>

}