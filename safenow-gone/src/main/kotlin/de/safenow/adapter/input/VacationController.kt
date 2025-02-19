package de.safenow.adapter.input

import de.safenow.application.service.EmployeeService
import de.safenow.application.service.VacationService
import de.safenow.domain.Vacation
import de.safenow.domain.VacationStatus
import jakarta.ws.rs.*
import java.time.LocalDate
import java.util.*

@Path("/vacations")
class VacationController() {

    private val vacations = VacationService()
    private val employeeService = EmployeeService()

    @DELETE
    @Path("/{id}")
    fun deleteOne(id: UUID): Boolean = vacations.delete(id)

    @GET
    fun get(@QueryParam("from") from: String?, @QueryParam("to") to: String?): List<VacationDTO> {
        if (from == null) {
            return vacations.getAll().map { map(it) }
        }
        // Default ISO Format (YYYY-MM-DD)
        return vacations.getWithRange(LocalDate.parse(from), to?.let { LocalDate.parse(it) }).map { map(it) }
    }

    @GET
    @Path("/{id}")
    fun getOne(id: UUID): Vacation? =
        vacations.get(id) ?: throw IllegalArgumentException("Vacation not found")


    @PATCH
    @Path("/{id}")
    fun updateOne(id: UUID, dto: PartialVacationUpdateDTO): Vacation {
        val v = vacations.get(id) ?: throw IllegalArgumentException("Vacation not found")
        return vacations.update(v, dto.status)
    }

    data class PartialVacationUpdateDTO(
        val status: VacationStatus
    )

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
        )
    }

    fun map(v: Vacation): VacationDTO {
        return VacationDTO(
            id = v.id,
            from = v.from,
            to = v.to,
            takingEmployeeId = v.takingEmployee.id!!
        )
    }


    data class VacationDTO(
        val id: UUID? = null,
        val from: LocalDate,
        val to: LocalDate,
        val takingEmployeeId: Int,
    )

}