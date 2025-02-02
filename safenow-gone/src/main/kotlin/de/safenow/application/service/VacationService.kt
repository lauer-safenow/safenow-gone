package de.safenow.application.service

import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.port.input.vacation.GetVacationUsecase
import de.safenow.port.input.vacation.SaveVacationUsecase
import java.util.*

class VacationService() : SaveVacationUsecase, GetVacationUsecase {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

    override fun save(vacation: Vacation): Vacation  = persistenceOutputPort.save(vacation)

    override fun get(id: UUID): Vacation? = persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> = persistenceOutputPort.getAll()

}
