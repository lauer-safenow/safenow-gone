package de.safenow.application.service

import de.safenow.adapter.out.EmployeeRepositoryAdapter
import de.safenow.domain.Employee
import de.safenow.port.`in`.GetEmployeeUsecase
import de.safenow.port.`in`.SaveEmployeeUsecase
import de.safenow.port.out.EmployeeRepositoryOutputPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmployeeService() : SaveEmployeeUsecase, GetEmployeeUsecase {

    private val employeeRepositoryOutputPort: EmployeeRepositoryOutputPort = EmployeeRepositoryAdapter()

    override fun save(employee: Employee) =
        employeeRepositoryOutputPort.save(employee)

    override fun get(id: Int): Employee? =
        employeeRepositoryOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        employeeRepositoryOutputPort.getAll()

}
