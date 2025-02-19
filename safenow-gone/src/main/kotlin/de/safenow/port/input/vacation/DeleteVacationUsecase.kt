package de.safenow.port.input.vacation

import java.util.*

interface DeleteVacationUsecase {

    fun delete(id: UUID): Boolean

}