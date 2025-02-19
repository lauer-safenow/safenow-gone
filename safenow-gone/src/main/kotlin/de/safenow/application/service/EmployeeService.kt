package de.safenow.application.service

import de.safenow.adapter.output.EmployeePersistenceAdapter
import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import de.safenow.domain.removeAbsence
import de.safenow.port.input.employee.DeleteEmployeeUsecase
import de.safenow.port.input.employee.GetEmployeeUsecase
import de.safenow.port.input.employee.SaveEmployeeUsecase
import java.util.UUID

class EmployeeService() : SaveEmployeeUsecase, GetEmployeeUsecase, DeleteEmployeeUsecase {

    private val persistenceOutputPort = EmployeePersistenceAdapter()


    fun saveNew(e: Employee): Employee {
        getByEMail(e.email)?.let { throw IllegalArgumentException("Employee with email ${e.email} already exists") }
        return persistenceOutputPort.save(e)
    }

    override fun save(e: Employee): Employee {
        return persistenceOutputPort.save(e)
    }

    override fun get(id: Int): Employee? =
        persistenceOutputPort.getById(id)

    override fun getAll(): List<Employee> =
        persistenceOutputPort.getAll()

    override fun getByEMail(email: String): Employee? =
        persistenceOutputPort.getByEmail(email)

    override fun delete(id: Int): Boolean = persistenceOutputPort.delete(id)

    fun removeAbsence(employeeId: Int, vacation : Vacation): Boolean {
        val employee = get(employeeId) ?: return false
        employee.removeAbsence(vacation)
        save(employee)
        return true
    }


}
