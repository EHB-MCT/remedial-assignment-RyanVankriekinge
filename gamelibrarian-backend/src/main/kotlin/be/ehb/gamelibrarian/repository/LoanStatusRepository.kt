package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.LoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface LoanStatusRepository : JpaRepository<LoanStatus, Long> {
    fun existsByStatusCode(statusCode: String): Boolean
}
