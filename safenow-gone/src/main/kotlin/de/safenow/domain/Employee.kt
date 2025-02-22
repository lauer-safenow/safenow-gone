package de.safenow.domain

import java.time.LocalDate

data class Employee(
    val id: Int? = null,
    val name: String,
    val email: String,
    val absences: Set<Absence> = setOf(), // UNDERSTAND MUTABLE SET ERROR HERE?!?!?
    val daysTaken: Int = 0,
    val daysMax: Int = 30
)

data class Absence(
    val from: LocalDate,
    val to: LocalDate,
    val reason: String,
)


fun Employee.removeAbsence(vacation: Vacation): Pair<Boolean, Employee> {
    // TODO check if the absence is a vacation and is in the future
    val wasAbsenceRemoved = this.absences.toMutableSet().removeIf { it.from == vacation.from && it.to == vacation.to }
    if (wasAbsenceRemoved) {
        val newDaysTaken = vacation.from.until(vacation.to).days + 1 - this.daysTaken
        return true to this.copy(daysTaken = newDaysTaken)
    }
    return false to this
}

fun Employee.addAbsence(v: Vacation): Pair<Boolean, Employee> {
    // TODO check for time overlaps not only relying on SET
    val from = v.from
    val to = v.to
    val isOverlapping =
        this.absences.any { (it.from <= from && it.to >= from) || (it.from <= to && it.to >= to) || (it.from == from && it.to == to) }
    if (!isOverlapping) {
        val newAbsences = this.absences.toMutableSet()
        newAbsences.add(Absence(from, to, "Vacation"))
        val newDaysTaken = v.from.until(v.to).days + 1 + this.daysTaken
        return true to this.copy(daysTaken = newDaysTaken, absences = newAbsences)
    } else {
        return false to this
    }


}


