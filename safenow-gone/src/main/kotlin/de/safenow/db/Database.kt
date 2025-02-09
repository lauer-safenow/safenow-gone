package de.safenow.db

import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import de.safenow.domain.addAbsence
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

            val vacationE1 = Vacation(
                id= UUID.fromString("452c9a70-9b1d-4514-8bdb-9ab72f55a6e1"),
                from = LocalDate.now().minusDays(2),
                to = LocalDate.now().plusDays(5),
                takingEmployee = savedE2,
                standInEmployee = savedE1,
            )

            val vacation = Vacation(
                id= UUID.fromString("452c9a70-9b1d-4514-8bdb-9ab72f55a6e1"),
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



            savedE2.addAbsence(vacationE1)
            savedE1.addAbsence(vacation)
            savedE1.addAbsence(vacation2)

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
            val id = vacation.id ?: UUID.randomUUID()
            val copy = vacation.copy(id = id)
            vacationTable[copy.id!!] = copy
            return copy
        }

        fun getVacation(id: UUID): Vacation? = vacationTable[id]
        fun getVacations(): List<Vacation> = vacationTable.values.toList()
    }


}