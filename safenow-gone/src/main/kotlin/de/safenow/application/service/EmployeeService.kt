package de.safenow.application.service

import de.safenow.adapter.output.EmployeePersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.port.input.employee.GetUsecase
import de.safenow.port.input.employee.SaveUsecase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmployeeService() : SaveUsecase<Employee>, GetUsecase<Employee> {

    private val persistenceOutputPort = EmployeePersistenceAdapter()

    override fun save(employee: Employee) =
        persistenceOutputPort.save(employee)

    override fun get(id: Int): Employee? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        persistenceOutputPort.getAll()

}
