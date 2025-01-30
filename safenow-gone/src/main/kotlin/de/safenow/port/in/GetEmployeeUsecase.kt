package de.safenow.port.`in`

import de.safenow.domain.Employee

interface GetEmployeeUsecase {

    fun get(id: Int): Employee?
    fun getAll(): List<Employee>

}