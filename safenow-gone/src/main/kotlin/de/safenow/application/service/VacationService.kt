package de.safenow.application.service

import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.domain.VacationStatus
import de.safenow.domain.approve
import de.safenow.domain.cancel
import de.safenow.domain.reject
import de.safenow.port.input.vacation.GetVacationUsecase
import de.safenow.port.input.vacation.SaveVacationUsecase
import de.safenow.port.input.vacation.UpdateVacationUsecase
import java.util.*

class VacationService() : SaveVacationUsecase, GetVacationUsecase, UpdateVacationUsecase {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

    override fun update(vacation: Vacation): Vacation {
        val v = when(vacation.status){
            VacationStatus.PENDING -> vacation
            VacationStatus.APPROVED -> vacation.approve()
            VacationStatus.REJECTED ->  vacation.reject()
            VacationStatus.CANCELLED -> vacation.cancel()
        }
        return persistenceOutputPort.save(v)
    }

    override fun save(v: Vacation): Vacation  {
        return persistenceOutputPort.save(v)
    }

    override fun get(id: UUID): Vacation? = persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> = persistenceOutputPort.getAll()



}
