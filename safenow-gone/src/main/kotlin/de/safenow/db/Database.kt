package de.safenow.db

import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import java.time.Instant
import java.time.LocalDate
import java.util.*


class Database {

    companion object {
        val employeeTable: MutableMap<Int, Employee> = mutableMapOf()
        var nextIdEmployee: Int = 0

        val vacationTable: MutableMap<UUID, Vacation> = mutableMapOf()

        init {
            val employee = Employee(name = "Developer")
            val employee1 = Employee(name = "Manager")
            val savedE1 = saveEmployee(employee)
            val savedE2 = saveEmployee(employee1)

            val vacation= Vacation(from= LocalDate.now(), to = LocalDate.now(), takingEmployee =  savedE1, standInEmployee = savedE2)
            saveVacation(vacation)
        }




        fun saveEmployee(employee: Employee) : Employee {
            val copy = employee.copy(id = nextIdEmployee)
            employeeTable[nextIdEmployee] = copy
            nextIdEmployee++
            return copy
        }

        fun getEmployee(id: Int): Employee? = employeeTable[id]
        fun getEmployees(): List<Employee> = employeeTable.values.toList()

        fun saveVacation(v: Vacation) : Vacation {
            val copy = v.copy()
            vacationTable[v.id!!] = copy
            return copy
        }
        fun getVacation(id: UUID): Vacation? = vacationTable[id]
        fun getVacations(): List<Vacation> = vacationTable.values.toList()
    }


}