package de.safenow.domain

import java.time.LocalDate
import java.util.*

data class Vacation(
    val id: UUID? = null,
    val from: LocalDate,
    val to: LocalDate,
    val takingEmployee: Employee, // TODO use ID instead of ref here?
    val status: VacationStatus = VacationStatus.PENDING
)

enum class VacationStatus {
    PENDING, APPROVED, REJECTED, CANCELLED
}

fun Vacation.approve(): Vacation {
    this.takingEmployee.addAbsence(this)
    return this.copy(status = VacationStatus.APPROVED)
}

fun Vacation.reject(): Vacation {
    this.takingEmployee.removeAbsence(this)
    return this.copy(status = VacationStatus.REJECTED)
}

fun Vacation.cancel(): Vacation {
    this.takingEmployee.removeAbsence(this)
    return this.copy(status = VacationStatus.CANCELLED)
}



fun Vacation.updateStatus(status: VacationStatus) : Vacation {
   return when(status){
        VacationStatus.PENDING -> this
        VacationStatus.APPROVED -> this.approve()
        VacationStatus.REJECTED ->  this.reject()
        VacationStatus.CANCELLED -> this.cancel()
    }
}
