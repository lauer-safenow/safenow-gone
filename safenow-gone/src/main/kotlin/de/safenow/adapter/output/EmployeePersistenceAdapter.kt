package de.safenow.adapter.output

import de.safenow.db.Database
import de.safenow.domain.Employee
import de.safenow.port.output.PersistenceOutputPort

class EmployeePersistenceAdapter() : PersistenceOutputPort<Int, Employee> {

    override fun save(employee: Employee): Employee = Database.saveEmployee(employee)
    override fun getAll(): List<Employee> = Database.getEmployees()
    override fun delete(id: Int): Boolean = Database.deleteEmployee(id)
    override fun getById(id: Int): Employee? = Database.getEmployee(id)

    // TODO Improve this not being part of the interface
    fun getByEmail(email: String): Employee? = Database.getEmployeeByEmail(email)


}