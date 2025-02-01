package de.safenow.adapter.output

import de.safenow.domain.Vacation
import de.safenow.port.output.PersistenceOutputPort
import java.util.*

class VacationsPersistenceAdapter() : PersistenceOutputPort<UUID, Vacation> {

    private val db: MutableMap<UUID, Vacation> = mutableMapOf()


    override fun save(v: Vacation) {
        db[v.id] = v.copy()
    }

    override fun getAll(): List<Vacation> = db.values.toList()
    override fun getById(id: UUID): Vacation? = db[id]


}