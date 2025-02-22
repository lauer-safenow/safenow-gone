package de.safenow.db

import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import de.safenow.domain.addAbsence
import java.time.LocalDate
import java.util.*


// this is lazy loaded
class Database {


    companion object {
        val employeeTable: MutableMap<Int, Employee> = mutableMapOf()
        var nextIdEmployee: Int = 0

        val vacationTable: MutableMap<UUID, Vacation> = mutableMapOf()

        init {
            val savedE1 = saveEmployee(Employee(name = "Developer", email = "dev@b.c"))
            saveEmployee(Employee(name = "Manager", email = "manager@b.c"))


            val vacation = Vacation(
                from = LocalDate.now().minusDays(20),
                to = LocalDate.now(),
                takingEmployee = savedE1,
            )

            val vacation2 = Vacation(
                from = LocalDate.now().plusDays(1),
                to = LocalDate.now().plusDays(10),
                takingEmployee = savedE1,
            )

            val saveVacation = saveVacation(vacation)
            val saveVacation1 = saveVacation(vacation2)

            val empWithAbsence = savedE1.addAbsence(saveVacation)
            val savedEmployee = saveEmployee(empWithAbsence)

            val empWithAbsence1 = savedEmployee.addAbsence(saveVacation1)
            saveEmployee(empWithAbsence1)




        }

        fun deleteVacation(id: UUID): Boolean = vacationTable.remove(id) != null

        fun saveEmployee(employee: Employee): Employee {
            if (employee.id != null) {
                val copy = employee.copy()
                employeeTable[employee.id] = copy
                return copy
            }

            val copy = employee.copy(id = nextIdEmployee)
            employeeTable[nextIdEmployee] = copy
            nextIdEmployee++
            return copy
        }

        fun getEmployee(id: Int): Employee? = employeeTable[id]
        fun getEmployees(): List<Employee> = employeeTable.values.toList()
        fun deleteEmployee(id: Int): Boolean = employeeTable.remove(id) != null
        fun getEmployeeByEmail(email: String): Employee? =
            employeeTable.values.let { e ->
                e.find {
                    it.email == email
                }
            }


        fun saveVacation(vacation: Vacation): Vacation {
            if (vacation.id != null) {
                val copy = vacation.copy()
                vacationTable[vacation.id] = copy
                return copy
            }

            val id = vacation.id ?: UUID.randomUUID()
            val copy = vacation.copy(id = id, takingEmployee = vacation.takingEmployee.copy())
            vacationTable[copy.id!!] = copy
            return copy
        }

        fun getVacation(id: UUID): Vacation? = vacationTable[id]
        fun getVacations(): List<Vacation> = vacationTable.values.toList()

        fun getVacationsWithRange(from: LocalDate, to: LocalDate?): List<Vacation> =
            vacationTable.values.filter {
                it.from.isEqual(from) || it.from.isAfter(from) &&
                        (to == null || it.to.isEqual(to) || it.to.isBefore(to)) // allow open ended range with to == null
            }
    }


}