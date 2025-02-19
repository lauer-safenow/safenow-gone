package de.safenow.application.service


import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.domain.VacationStatus
import de.safenow.domain.addAbsence
import de.safenow.domain.removeAbsence
import de.safenow.domain.updateStatus
import de.safenow.port.input.vacation.DeleteVacationUsecase
import de.safenow.port.input.vacation.GetVacationUsecase
import de.safenow.port.input.vacation.SaveVacationUsecase
import de.safenow.port.input.vacation.UpdateVacationUsecase
import java.time.LocalDate
import java.util.*

class VacationService() : SaveVacationUsecase, GetVacationUsecase, UpdateVacationUsecase, DeleteVacationUsecase {

    private val persistenceOutputPort = VacationsPersistenceAdapter()
    private val employeeService = EmployeeService()

    override fun update(vacation: Vacation, status: VacationStatus): Vacation {
        val v = vacation.updateStatus(status)
        return persistenceOutputPort.save(v)
    }

    override fun save(v: Vacation): Vacation {
        //TODO persist the absence
        v.takingEmployee.addAbsence(v)
        return persistenceOutputPort.save(v)
    }

    override fun get(id: UUID): Vacation? = persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> = persistenceOutputPort.getAll()

    override fun getWithRange(from: LocalDate, to: LocalDate?): List<Vacation> =
        persistenceOutputPort.getWithRange(from, to)

    override fun delete(id: UUID): Boolean {
        val vacation = get(id) ?: return false
        employeeService.removeAbsence(vacation.takingEmployee.id!!, vacation)
        return persistenceOutputPort.delete(id)
    }


}


