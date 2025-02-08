package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.application.service.VacationService
import de.safenow.domain.Vacation
import de.safenow.domain.VacationStatus
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import java.time.LocalDate
import java.util.*

@Path("/vacations")
class VacationController() {

    private val vacations = VacationService()
    private val employeeService = EmployeeService()

    @GET
    fun getAll(): List<Vacation> {
        println("Getting all Vacations...")
        return vacations.getAll()
    }

    @GET
    @Path("/{id}")
    fun getOne(id: UUID): Vacation? = vacations.get(id)

    @PUT
    @Path("/{id}")
    fun updateOne(id: UUID, vacation: VacationDTO): Vacation {
        val v = map(vacation).copy(id = id)
        return vacations.update(v)
    }

    @POST
    fun saveOne(vacation: VacationDTO): Vacation {
        val v = map(vacation)
        return vacations.save(v)
    }

    fun map(dto: VacationDTO): Vacation {
        return Vacation(
            id = dto.id,
            from = dto.from,
            to = dto.to,
            takingEmployee = employeeService.get(dto.takingEmployeeId)
                ?: throw IllegalArgumentException("Taking employee not found"),
            standInEmployee = employeeService.get(dto.standInEmployeeId)
                ?: throw IllegalArgumentException("Stand-in employee not found"),
            status = dto.status
        )
    }

    data class VacationDTO(
        val id: UUID? = null,
        val from: LocalDate,
        val to: LocalDate,
        val takingEmployeeId: Int,
        val standInEmployeeId: Int,
        val status: VacationStatus
    )

}