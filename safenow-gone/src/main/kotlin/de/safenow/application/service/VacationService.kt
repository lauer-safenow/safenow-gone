package de.safenow.application.service

import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.port.input.employee.GetEmployeeUsecase
import de.safenow.port.input.employee.SaveEmployeeUsecase
import de.safenow.port.input.vacation.GetVacationUsecase
import de.safenow.port.input.vacation.SaveVacationUsecase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class VacationService() : SaveVacationUsecase, GetVacationUsecase {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

    override fun save(v: Vacation) =
        persistenceOutputPort.save(v)

    override fun get(id: UUID): Vacation? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> =
        persistenceOutputPort.getAll()

}
