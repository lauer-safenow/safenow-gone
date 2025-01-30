package de.safenow.adapter.out

import de.safenow.domain.Employee
import de.safenow.port.out.EmployeePersistenceOutputPort

class EmployeePersistenceAdapter() : EmployeePersistenceOutputPort {

    private val db: MutableMap<Int, Employee> = mutableMapOf()
    private var nextId: Int = 0

    override fun save(employee: Employee) {
        db[nextId] = employee.copy(id = nextId)
        nextId++
    }

    override fun getAll(): List<Employee> = db.values.toList()
    override fun getById(id: Int): Employee? = db[id]


}