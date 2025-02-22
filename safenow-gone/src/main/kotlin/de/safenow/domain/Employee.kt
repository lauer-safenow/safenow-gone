package de.safenow.domain

import com.aayushatharva.brotli4j.common.annotations.Local
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

fun Employee.addAbsence(v: Vacation): Employee {
    validateVacationParams(v)
    validateOverlapping(v)

    val newAbsences = this.absences.toMutableSet()
    newAbsences.add(Absence(v.from, v.to, "Vacation"))
    val newDaysTaken = 1 + v.from.until(v.to).days + this.daysTaken
    return this.copy(daysTaken = newDaysTaken, absences = newAbsences)


}

private fun Employee.validateVacationParams(v: Vacation) {
    if (v.from.isAfter(v.to)) {
        throw SomeException(
            """Vacation <<FROM>> may not be after <<TO>>
                    the past employee:${this.id} from:${v.from} to:${v.to}"""
        )
    }
}

private fun Employee.validateOverlapping(v: Vacation) {
    if (isOverlapping(v.from, v.to)) {
        throw SomeException("Vacation overlaps with existing absence employee:${this.id} from: ${v.from} to: ${v.to}")
    }
}

private fun Employee.isOverlapping(from: LocalDate, to: LocalDate): Boolean {
    val isOverlapping = this.absences.any {
        (it.from <= from && it.to >= from) || (it.from <= to && it.to >= to) || (it.from == from && it.to == to)
    }
    return isOverlapping
}


