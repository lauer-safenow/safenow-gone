package de.safenow.domain

import java.time.Instant

data class Vacation(val id: Int? = null, val from: Instant, val to: Instant, val employee: Employee, val standIn: Employee) {

}