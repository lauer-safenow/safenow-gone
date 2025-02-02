package de.safenow.port.input.vacation

import de.safenow.domain.Vacation

interface SaveVacationUsecase {

    fun save(vacation: Vacation) : Vacation
}