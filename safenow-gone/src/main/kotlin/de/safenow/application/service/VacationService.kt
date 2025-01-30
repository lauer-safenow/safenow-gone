package de.safenow.application.service

import de.safenow.adapter.output.EmployeePersistenceAdapter
import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import de.safenow.port.input.employee.GetUsecase
import de.safenow.port.input.employee.SaveUsecase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class VacationService() : SaveUsecase<Vacation>, GetUsecase<Vacation> {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

    override fun save(v: Vacation) =
        persistenceOutputPort.save(v)

    override fun get(id: Int): Vacation? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> =
        persistenceOutputPort.getAll()

}
