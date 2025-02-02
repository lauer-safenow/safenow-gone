package de.safenow.adapter.input

import de.safenow.application.service.VacationService
import de.safenow.domain.Vacation
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import java.util.*

@Path("/vacations")
class VacationController() {

    private val vacations = VacationService()

    @GET
    fun getAll(): List<Vacation> {
        println("Getting all Vacations...")
        return vacations.getAll()
    }

    @GET
    @Path("/{id}")
    fun getOne(id: UUID): Vacation? = vacations.get(id)

    @POST
    fun saveOne(vacation: Vacation) {
        vacations.save(vacation)
    }

}