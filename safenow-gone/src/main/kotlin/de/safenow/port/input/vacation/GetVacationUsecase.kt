package de.safenow.port.input.vacation

import de.safenow.domain.Vacation
import java.time.LocalDate
import java.util.*

interface GetVacationUsecase {

    fun get(id: UUID): Vacation?
    fun getAll(): List<Vacation>
    fun getWithRange(from : LocalDate, to: LocalDate?): List<Vacation>

}