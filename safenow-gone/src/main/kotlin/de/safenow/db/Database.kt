package de.safenow.db

import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import java.time.LocalDate
import java.util.*


class Database {

    companion object {
        val employeeTable: MutableMap<Int, Employee> = mutableMapOf()
        var nextIdEmployee: Int = 0

        val vacationTable: MutableMap<UUID, Vacation> = mutableMapOf()

        init {
            val savedE1 = saveEmployee(Employee(name = "Developer", email = "dev@b.c"))
            val savedE2 = saveEmployee(Employee(name = "Manager", email = "manager@b.c"))

            val vacation = Vacation(
                from = LocalDate.now(),
                to = LocalDate.now(),
                takingEmployee = savedE1,
                standInEmployee = savedE2,
            )

            val vacation2 = Vacation(
                from = LocalDate.now(),
                to = LocalDate.now().plusDays(10),
                takingEmployee = savedE1,
                standInEmployee = savedE2
            )

            saveVacation(vacation)
            saveVacation(vacation2)

        }


        fun saveEmployee(employee: Employee): Employee {
            val copy = employee.copy(id = nextIdEmployee)
            employeeTable[nextIdEmployee] = copy
            nextIdEmployee++
            return copy
        }

        fun getEmployee(id: Int): Employee? = employeeTable[id]
        fun getEmployees(): List<Employee> = employeeTable.values.toList()

        fun saveVacation(vacation: Vacation): Vacation {
            val copy = vacation.copy(id = UUID.randomUUID())
            vacationTable[copy.id!!] = copy
            return copy
        }

        fun getVacation(id: UUID): Vacation? = vacationTable[id]
        fun getVacations(): List<Vacation> = vacationTable.values.toList()
    }


}