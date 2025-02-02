package de.safenow.domain

import java.time.LocalDate
import java.util.*

data class Vacation(
    val id: UUID? = null,
    val from: LocalDate,
    val to: LocalDate,
    val takingEmployee: Employee,
    val standInEmployee: Employee
)