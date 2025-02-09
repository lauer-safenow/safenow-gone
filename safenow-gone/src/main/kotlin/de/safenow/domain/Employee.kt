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

fun Employee.removeAbsence(vacation: Vacation) {
    vacation.standInEmployee.removeStandIn(vacation)
    this.absences.removeIf { it.from == vacation.from && it.to == vacation.to }
}

fun Employee.addAbsence(v: Vacation)  {
    v.standInEmployee.addStandIn(v)
    this.absences.add(Absence(v.from, v.to, "Vacation"))
}

fun Employee.addStandIn(v: Vacation)  {
    this.standIns.add(StandIn(v.from, v.to, v.takingEmployee.id!!))
}

fun Employee.removeStandIn(v: Vacation)  {
    this.standIns.removeIf{ it.from == v.from && it.to == v.to }
}


