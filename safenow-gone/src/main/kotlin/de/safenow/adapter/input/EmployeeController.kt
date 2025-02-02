package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/employees")
class EmployeeController() {

    private val employeeService = EmployeeService()

    @GET
    fun getAll(): List<Employee> = employeeService.getAll()

    @GET
    @Path("/{id}")
    fun getOne(id: Int): Employee? = employeeService.get(id)

    @POST
    fun saveOne(e: Employee) = employeeService.save(e)

}