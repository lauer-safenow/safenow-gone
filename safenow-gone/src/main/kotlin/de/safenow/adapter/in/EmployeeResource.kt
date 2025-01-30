package de.safenow.adapter.`in`

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import de.safenow.port.`in`.GetEmployeeUsecase
import de.safenow.port.`in`.SaveEmployeeUsecase
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/employees")
class EmployeeResource() {

    @Inject
    lateinit var employeeService: EmployeeService

    @GET
    fun getEmployees(): List<Employee> = employeeService.getAll()

}