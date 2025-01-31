package de.safenow.application.service

import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.port.input.employee.GetUsecase
import de.safenow.port.input.employee.SaveUsecase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class VacationService() : SaveUsecase<Vacation>, GetUsecase<UUID, Vacation> {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

    override fun save(v: Vacation) =
        persistenceOutputPort.save(v)

    override fun get(id: UUID): Vacation? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> =
        persistenceOutputPort.getAll()

}
