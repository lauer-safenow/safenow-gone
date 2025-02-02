package de.safenow.port.output

interface PersistenceOutputPort<ID,TYPE> {
    fun save(t: TYPE) : TYPE
    fun getById(id: ID): TYPE?
    fun getAll(): List<TYPE>
}