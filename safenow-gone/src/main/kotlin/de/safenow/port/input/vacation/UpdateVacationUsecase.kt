package de.safenow.port.input.vacation

import de.safenow.domain.Vacation

interface UpdateVacationUsecase {

    fun update(vacation: Vacation) : Vacation
}