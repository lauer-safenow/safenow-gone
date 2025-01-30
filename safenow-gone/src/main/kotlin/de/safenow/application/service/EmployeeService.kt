package de.safenow.application.service

import de.safenow.adapter.out.EmployeePersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.port.`in`.GetEmployeeUsecase
import de.safenow.port.`in`.SaveEmployeeUsecase
import de.safenow.port.out.EmployeePersistenceOutputPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmployeeService() : SaveEmployeeUsecase, GetEmployeeUsecase {

    private val employeePersistenceOutputPort: EmployeePersistenceOutputPort = EmployeePersistenceAdapter()

    override fun save(employee: Employee) =
        employeePersistenceOutputPort.save(employee)

    override fun get(id: Int): Employee? =
        employeePersistenceOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        employeePersistenceOutputPort.getAll()

}
