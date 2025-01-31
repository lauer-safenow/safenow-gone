package de.safenow.domain

import java.time.Instant
import java.util.UUID

data class Vacation(val id: UUID = UUID.randomUUID(), val from: Instant, val to: Instant, val employee: Employee, val standIn: Employee) {

}