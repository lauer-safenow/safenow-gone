package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.application.service.VacationService
import de.safenow.domain.Vacation
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
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

    @POST
    fun saveOne(vacation: VacationDTO): Vacation {
        val v = map(vacation)
        return vacations.save(v)
    }

    fun map(dto: VacationDTO): Vacation {
        return Vacation(
            from = dto.from,
            to = dto.to,
            takingEmployee = employeeService.get(dto.takingEmployeeId)
                ?: throw IllegalArgumentException("Taking employee not found"),
            standInEmployee = employeeService.get(dto.standInEmployeeId)
                ?: throw IllegalArgumentException("Stand-in employee not found"),
        )
    }

    data class VacationDTO(
        val from: LocalDate,
        val to: LocalDate,
        val takingEmployeeId: Int,
        val standInEmployeeId: Int
    )

}