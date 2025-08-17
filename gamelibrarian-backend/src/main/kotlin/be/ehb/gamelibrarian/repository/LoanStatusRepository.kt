package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.LoanStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LoanStatusRepository : JpaRepository<LoanStatus, Long> {
    fun existsByStatusCode(statusCode: String): Boolean
    fun findByStatusCode(statusCode: String): Optional<LoanStatus>
}
