package de.safenow.port.input.vacation

import de.safenow.domain.Vacation
import java.util.UUID

interface GetVacationUsecase {

    fun get(id: UUID): Vacation?
    fun getAll(): List<Vacation>

}