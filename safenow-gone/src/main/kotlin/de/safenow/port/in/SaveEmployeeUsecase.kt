package de.safenow.port.`in`

import de.safenow.domain.Employee

interface SaveEmployeeUsecase {

    fun save(employee: Employee)
}