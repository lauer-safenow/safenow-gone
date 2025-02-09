package de.safenow.domain

import java.time.LocalDate

data class Employee(
    val id: Int? = null,
    val name: String,
    val email: String,
    val absences: MutableSet<Absence> = mutableSetOf(),
    val standIns: MutableList<StandIn> = mutableListOf()
    )

data class StandIn(
    val from: LocalDate,
    val to: LocalDate,
    val forEmployee: Int
)

data class Absence(
    val from: LocalDate,
    val to: LocalDate,
    val reason: String,
)

private fun isDateInRange(date: LocalDate, startDate: LocalDate, endDate: LocalDate): Boolean {
    return (date.isEqual(startDate) || date.isAfter(startDate)) &&
            (date.isEqual(endDate) || date.isBefore(endDate))
}

fun Employee.isAvailable(from: LocalDate, to: LocalDate): Boolean {
    return this.absences.none { isDateInRange(from, it.from, it.to) || isDateInRange(to, it.from, it.to) }
}

fun Employee.removeAbsence(vacation: Vacation) {
    vacation.standInEmployee.removeStandIn(vacation)
    this.absences.removeIf { it.from == vacation.from && it.to == vacation.to }
}

fun Employee.addAbsence(v: Vacation)  {
    if(!v.standInEmployee.isAvailable(v.from, v.to)) {
        throw IllegalArgumentException("Stand-in employee is already on vacation")
    }
    v.standInEmployee.addStandIn(v)
    this.absences.add(Absence(v.from, v.to, "Vacation"))
}

fun Employee.addStandIn(v: Vacation)  {
    this.standIns.add(StandIn(v.from, v.to, v.takingEmployee.id!!))
}

fun Employee.removeStandIn(v: Vacation)  {
    this.standIns.removeIf{ it.from == v.from && it.to == v.to }
}


