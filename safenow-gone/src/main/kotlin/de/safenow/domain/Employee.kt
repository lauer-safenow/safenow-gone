package de.safenow.domain

import java.time.LocalDate

data class Employee(
    val id: Int? = null,
    val name: String,
    val email: String,
    val absences: MutableSet<Absence> = mutableSetOf(),
    var daysTaken: Int = 0,
    val daysMax: Int = 30
)

data class Absence(
    val from: LocalDate,
    val to: LocalDate,
    val reason: String,
)


fun Employee.removeAbsence(vacation: Vacation) {
    // TODO check if the absence is a vacation and is in the future
    val wasAbsenceRemoved = this.absences.removeIf { it.from == vacation.from && it.to == vacation.to }
    if (wasAbsenceRemoved) {
        this.daysTaken -= vacation.from.until(vacation.to).days + 1
    }
}

fun Employee.addAbsence(v: Vacation) {
    val wasAbsenceAdded = this.absences.add(Absence(v.from, v.to, "Vacation"))
    if (wasAbsenceAdded) {
        this.daysTaken += v.from.until(v.to).days + 1
    }
}


