package de.safenow.adapter.output

import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import de.safenow.port.output.PersistenceOutputPort
import java.util.UUID

class VacationsPersistenceAdapter() : PersistenceOutputPort<Vacation> {

    private val db: MutableMap<Int, Vacation> = mutableMapOf()
    private var nextId: Int = 0


    override fun save(v: Vacation) {
        db[nextId] = v.copy(id = nextId)
        nextId++
    }

    override fun getAll(): List<Vacation> = db.values.toList()
    override fun getById(id: Int): Vacation? = db[id]


}