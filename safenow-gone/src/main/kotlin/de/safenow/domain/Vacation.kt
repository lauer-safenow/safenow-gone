package de.safenow.domain

import java.time.LocalDate
import java.util.*

data class Vacation(
    val id: UUID? = null,
    val from: LocalDate,
    val to: LocalDate,
    val takingEmployee: Employee,
    val standInEmployee: Employee,
    val status: VacationStatus = VacationStatus.PENDING
)

enum class VacationStatus {
    PENDING, APPROVED, REJECTED, CANCELLED
}

fun Vacation.approve(): Vacation {
    return this.copy(status = VacationStatus.APPROVED)
}

fun Vacation.reject(): Vacation {
    return this.copy(status = VacationStatus.REJECTED)
}

fun Vacation.cancel(): Vacation {
    return this.copy(status = VacationStatus.CANCELLED)
}
