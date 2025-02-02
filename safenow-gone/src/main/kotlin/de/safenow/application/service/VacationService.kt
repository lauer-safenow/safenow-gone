package de.safenow.application.service

import de.safenow.adapter.output.VacationsPersistenceAdapter
import de.safenow.domain.Vacation
import de.safenow.port.input.vacation.GetVacationUsecase
import de.safenow.port.input.vacation.SaveVacationUsecase
import java.util.*

class VacationService() : SaveVacationUsecase, GetVacationUsecase {

    private val persistenceOutputPort = VacationsPersistenceAdapter()

//    private val employeeService = EmployeeService()

    override fun save(v: Vacation) = {
//        val takingEmployee: Employee = v.employee
//        val standinEmployee: Employee = v.standIn
        persistenceOutputPort.save(v)
    }

    override fun get(id: UUID): Vacation? = persistenceOutputPort.getById(id)

    override fun getAll(): List<Vacation> = persistenceOutputPort.getAll()

}
