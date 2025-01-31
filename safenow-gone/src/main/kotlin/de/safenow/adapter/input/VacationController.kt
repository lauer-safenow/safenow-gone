package de.safenow.adapter.input

import de.safenow.application.service.VacationService
import de.safenow.domain.Vacation
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import java.util.*

@Path("/vacations")
class VacationController() {

    @Inject
    lateinit var vacations: VacationService

    @GET
    fun getAll(): List<Vacation> = vacations.getAll()

    @GET
    @Path("/{id}")
    fun getOne(id: UUID): Vacation? = vacations.get(id)

    @POST
    fun saveOne(e: Vacation) = vacations.save(e)

}