package de.safenow.port.out

import de.safenow.domain.Employee

interface EmployeePersistenceOutputPort {
    fun save(employee: Employee)
    fun getById(employeeId: Int): Employee?
    fun getAll(): List<Employee>

}