package de.safenow.adapter.output

import de.safenow.db.Database
import de.safenow.domain.Vacation
import de.safenow.port.output.PersistenceOutputPort
import java.time.LocalDate
import java.util.*

class VacationsPersistenceAdapter() : PersistenceOutputPort<UUID, Vacation> {

    override fun save(vacation: Vacation) : Vacation{
       return Database.saveVacation(vacation)
    }

    override fun getAll(): List<Vacation> = Database.getVacations()
    override fun getById(id: UUID): Vacation? = Database.getVacation(id)
    // TODO Improve this
    fun getWithRange(from: LocalDate, to: LocalDate?): List<Vacation> = Database.getVacationsWithRange(from, to)


}