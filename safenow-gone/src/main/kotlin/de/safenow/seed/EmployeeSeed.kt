package de.safenow.seed

import de.safenow.application.service.EmployeeService
import de.safenow.domain.Employee
import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
@Startup
class EmployeeSeed(val e : EmployeeService) {

    @PostConstruct
    fun seedEmployees() {
        val andi = Employee(name="Andi")
        e.save(andi)
        e.save(andi)
        e.save(andi)
        e.save(andi)
        println("Seed data loaded successfully!")
    }

}