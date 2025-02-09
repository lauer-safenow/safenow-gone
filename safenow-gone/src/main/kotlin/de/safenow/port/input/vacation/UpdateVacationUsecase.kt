package de.safenow.port.input.vacation

import de.safenow.domain.Vacation
import de.safenow.domain.VacationStatus

interface UpdateVacationUsecase {

    fun update(vacation: Vacation, status: VacationStatus) : Vacation
}