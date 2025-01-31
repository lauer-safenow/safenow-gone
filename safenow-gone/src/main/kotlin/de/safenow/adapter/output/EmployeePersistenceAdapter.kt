package de.safenow.adapter.output

import de.safenow.domain.Employee
import de.safenow.port.output.PersistenceOutputPort

class EmployeePersistenceAdapter() : PersistenceOutputPort<Int, Employee> {

    private val db: MutableMap<Int, Employee> = mutableMapOf()
    private var nextId: Int = 0

    override fun save(employee: Employee) {
        db[nextId] = employee.copy(id = nextId)
        nextId++
    }

    override fun getAll(): List<Employee> = db.values.toList()
    override fun getById(id: Int): Employee? = db[id]


}