package de.safenow.application.service

import de.safenow.adapter.output.EmployeePersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.port.input.employee.GetEmployeeUsecase
import de.safenow.port.input.employee.SaveEmployeeUsecase

class EmployeeService() : SaveEmployeeUsecase, GetEmployeeUsecase {

    private val persistenceOutputPort = EmployeePersistenceAdapter()

    override fun save(e: Employee): Employee = persistenceOutputPort.save(e)

    override fun get(id: Int): Employee? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        persistenceOutputPort.getAll()

}
