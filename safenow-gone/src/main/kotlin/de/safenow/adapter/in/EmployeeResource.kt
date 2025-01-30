package de.safenow.adapter.`in`

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/employees")
class EmployeeResource() {

    @Inject
    lateinit var employeeService: EmployeeService

    @GET
    fun getEmployees(): List<Employee> = employeeService.getAll()

    @GET
    @Path("/{id}")
    fun getEmployees(id: Int): Employee? = employeeService.get(id)

    @POST
    fun saveOne(e: Employee) = employeeService.save(e)

}