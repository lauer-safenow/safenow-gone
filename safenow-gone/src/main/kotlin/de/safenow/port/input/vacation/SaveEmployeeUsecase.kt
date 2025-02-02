package de.safenow.port.input.vacation

import de.safenow.domain.Vacation

interface SaveVacationUsecase {

    fun save(e: Vacation): () -> Unit
}