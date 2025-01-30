package de.safenow.seed

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.inject.Singleton
@Startup
@Singleton
class EmployeeSeed(val e : EmployeeService) {

    @PostConstruct
    fun seedEmployees() {

        val m = Employee(name="Maria")
        val g = Employee(name="Guru")
        val j = Employee(name="Jaime")
        val a = Employee(name="Andi")

        e.save(m)
        e.save(g)
        e.save(j)
        e.save(a)
        println("Seed data loaded successfully!")
    }

}