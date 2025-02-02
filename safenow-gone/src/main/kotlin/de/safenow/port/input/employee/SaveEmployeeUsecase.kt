package de.safenow.port.input.employee

import de.safenow.domain.Employee

interface SaveEmployeeUsecase {

    fun save(employee: Employee) : Employee
}