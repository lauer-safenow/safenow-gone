package de.safenow.port.input.employee

interface SaveUsecase<T> {

    fun save(t: T)
}