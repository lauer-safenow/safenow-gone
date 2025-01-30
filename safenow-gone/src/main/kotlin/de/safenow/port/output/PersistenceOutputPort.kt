package de.safenow.port.output

interface PersistenceOutputPort<T> {
    fun save(t: T)
    fun getById(id: Int): T?
    fun getAll(): List<T>
}