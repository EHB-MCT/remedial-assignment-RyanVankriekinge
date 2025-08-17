package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.Lending
import be.ehb.gamelibrarian.repository.BoardgameRepository
import be.ehb.gamelibrarian.repository.LendingRepository
import be.ehb.gamelibrarian.repository.LoanStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LendingService(
    private val lendings: LendingRepository,
    private val statusRepository: LoanStatusRepository,
    private val boardgameRepository: BoardgameRepository
) {
    @Transactional
    fun create(lending: Lending): Lending {
        val existing = lendings.findByCopyCopyIdAndReturnedAtIsNull(lending.copy.copyId)
        if (existing != null) {
            throw IllegalStateException("This copy is already lent out")
        }
        val savedLending = lendings.save(lending)
        val boardgame = savedLending.copy.boardgame

        val updatedBoardgame = boardgame.copy(
            popularity = boardgame.popularity + 1
        )

        boardgameRepository.save(updatedBoardgame)

        return savedLending
    }

    fun get(id: Long): Lending =
        lendings.findById(id).orElseThrow { NoSuchElementException("Lending $id not found") }

    fun getAll(): List<Lending> = lendings.findAll()
    @Transactional
    fun markAsReturned(loanId: Long): Lending {
        val lending = lendings.findById(loanId)
            .orElseThrow { NoSuchElementException("Lending $loanId not found") }

        val returnedStatus = statusRepository.findByStatusCode("RETURNED")
            .orElseThrow { NoSuchElementException("LoanStatus RETURNED not found") }

        val updatedLending = lending.copy(
            returnedAt = LocalDateTime.now(),
            status = returnedStatus
        )
        return lendings.save(updatedLending)
    }
}
