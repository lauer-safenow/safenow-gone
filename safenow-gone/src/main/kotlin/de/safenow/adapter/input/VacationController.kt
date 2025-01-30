package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.application.service.VacationService
import de.safenow.domain.Employee
import de.safenow.domain.Vacation
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/vacations")
class VacationController() {

    @Inject
    lateinit var vacations: VacationService

    @GET
    fun getAll(): List<Vacation> = vacations.getAll()

    @GET
    @Path("/{id}")
    fun getOne(id: Int): Vacation? = vacations.get(id)

    @POST
    fun saveOne(e: Vacation) = vacations.save(e)

}