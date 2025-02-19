package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/employees")
class EmployeeController() {

    private val employeeService = EmployeeService()

    @DELETE
    @Path("/{id}")
    fun delete(id: Int): Boolean = employeeService.delete(id)

    @GET
    fun getAll(): List<Employee> = employeeService.getAll()

    @GET
    @Path("/{id}")
    fun getOne(id: Int): Employee? = employeeService.get(id)

    @POST
    fun saveOne(e: EmployeeDTO): Employee = employeeService.saveNew(
        Employee(
            name = e.name,
            email = e.email
        )
    )

    data class EmployeeDTO(
        val name: String,
        val email: String
    )

}