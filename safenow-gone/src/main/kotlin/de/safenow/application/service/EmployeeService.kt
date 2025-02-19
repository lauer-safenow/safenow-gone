package de.safenow.application.service

import de.safenow.adapter.output.EmployeePersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.port.input.employee.DeleteEmployeeUsecase
import de.safenow.port.input.employee.GetEmployeeUsecase
import de.safenow.port.input.employee.SaveEmployeeUsecase

class EmployeeService() : SaveEmployeeUsecase, GetEmployeeUsecase, DeleteEmployeeUsecase {

    private val persistenceOutputPort = EmployeePersistenceAdapter()



    override fun save(e: Employee): Employee {
        // Check if employee with email already exists
        getByEMail(e.email)?.let { throw IllegalArgumentException("Employee with email ${e.email} already exists") }
        return persistenceOutputPort.save(e)
    }

    override fun get(id: Int): Employee? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        persistenceOutputPort.getAll()

    override fun getByEMail(email: String): Employee? =
        persistenceOutputPort.getByEmail(email)

    override fun delete(id: Int): Boolean = persistenceOutputPort.delete(id)


}
