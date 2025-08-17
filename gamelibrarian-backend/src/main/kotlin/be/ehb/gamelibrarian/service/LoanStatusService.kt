package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.LoanStatus
import be.ehb.gamelibrarian.repository.LoanStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoanStatusService(
    private val statuses: LoanStatusRepository
) {
    @Transactional
    fun create(statusCode: String, statusText: String): LoanStatus {
        require(statusCode.isNotBlank()) { "Status code cannot be blank" }
        if (statuses.existsByStatusCode(statusCode)) {
            throw IllegalArgumentException("Status code already exists")
        }
        return statuses.save(LoanStatus(statusCode = statusCode, statusText = statusText))
    }

    fun get(id: Long): LoanStatus =
        statuses.findById(id).orElseThrow { NoSuchElementException("LoanStatus $id not found") }

    fun getAll(): List<LoanStatus> = statuses.findAll()
}
