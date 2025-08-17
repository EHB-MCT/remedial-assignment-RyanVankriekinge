package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.Lending
import be.ehb.gamelibrarian.repository.LendingRepository
import be.ehb.gamelibrarian.repository.LoanStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LendingService(
    private val lendings: LendingRepository,
    private val statusRepository: LoanStatusRepository
) {
    @Transactional
    fun create(lending: Lending): Lending =
        lendings.save(lending)

    fun get(id: Long): Lending =
        lendings.findById(id).orElseThrow { NoSuchElementException("Lending $id not found") }

    fun getAll(): List<Lending> = lendings.findAll()
    fun markAsReturned(loanId: Long): Lending {
        val lending = lendings.findById(loanId)
            .orElseThrow { NoSuchElementException("Lending $loanId not found") }

        val returnedStatus = statusRepository.findByStatusCode("RETURNED")
            .orElseThrow { NoSuchElementException("LoanStatus RETURNED not found") }

        val updatedLending = lending.copy(
            returnedAt = LocalDateTime.now(),
            status = returnedStatus
        )
        return updatedLending
    }
}
