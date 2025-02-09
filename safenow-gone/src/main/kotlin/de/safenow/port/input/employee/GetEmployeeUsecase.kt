package de.safenow.port.input.employee

import de.safenow.domain.Employee

interface GetEmployeeUsecase {

    fun get(id: Int): Employee?
    fun getAll(): List<Employee>
    fun getByEMail(email: String): Employee?

}