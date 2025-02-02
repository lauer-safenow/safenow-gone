package de.safenow.adapter.output

import de.safenow.db.Database
import de.safenow.domain.Vacation
import de.safenow.port.output.PersistenceOutputPort
import java.util.*

class VacationsPersistenceAdapter() : PersistenceOutputPort<UUID, Vacation> {

    override fun save(v: Vacation) {
        Database.saveVacation(v)
    }

    override fun getAll(): List<Vacation> = Database.getVacations()
    override fun getById(id: UUID): Vacation? = Database.getVacation(id)


}