package de.safenow.adapter.output

import de.safenow.db.Database
import de.safenow.domain.Employee
import de.safenow.port.output.PersistenceOutputPort

class EmployeePersistenceAdapter() : PersistenceOutputPort<Int, Employee> {

    override fun save(employee: Employee): Employee {
        return Database.saveEmployee(employee)
    }

    override fun getAll(): List<Employee> = Database.getEmployees()
    override fun getById(id: Int): Employee? = Database.getEmployee(id)


}