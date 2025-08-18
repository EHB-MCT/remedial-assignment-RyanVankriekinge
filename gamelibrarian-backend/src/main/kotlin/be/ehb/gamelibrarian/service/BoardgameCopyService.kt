package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.dto.BoardgameCopyDTO
import be.ehb.gamelibrarian.dto.UserBoardgameCopyDTO
import be.ehb.gamelibrarian.model.BoardgameCopy
import be.ehb.gamelibrarian.repository.BoardgameCopyRepository
import be.ehb.gamelibrarian.repository.BoardgameRepository
import be.ehb.gamelibrarian.repository.BoardgameUserRepository
import be.ehb.gamelibrarian.repository.LendingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardgameCopyService(
    private val copies: BoardgameCopyRepository,
    private val games: BoardgameRepository,
    private val users: BoardgameUserRepository,
    private val lendings: LendingRepository
) {
    @Transactional
    fun create(boardgameId: Long, ownerId: Long): BoardgameCopy {
        val game = games.findById(boardgameId)
            .orElseThrow { NoSuchElementException("Boardgame $boardgameId not found") }
        val owner = users.findById(ownerId)
            .orElseThrow { NoSuchElementException("User $ownerId not found") }

        return copies.save(BoardgameCopy(boardgame = game, owner = owner))
    }

    fun get(id: Long): BoardgameCopy =
        copies.findById(id).orElseThrow { NoSuchElementException("Copy $id not found") }

    fun getAll(): List<BoardgameCopy> = copies.findAll()

    fun getCopiesInfoForBoardgame(boardgameId: Long): List<BoardgameCopyDTO> {
        val boardgameCopies = copies.findAll().filter { it.boardgame.boardgameId == boardgameId }

        return boardgameCopies.map { copy ->
            val latestLending = copy.copyId.let { copyId ->
                lendings.findAll()
                    .filter { it.copy.copyId == copyId }
                    .maxByOrNull { it.loanTimestamp }
            }

            val status = latestLending?.status?.statusCode ?: "AVAILABLE"

            BoardgameCopyDTO(
                id = copy.copyId,
                ownerName = copy.owner.name,
                status = status
            )
        }
    }
    @Transactional(readOnly = true)
    fun getCopiesByUser(userId: Long): List<UserBoardgameCopyDTO> {
        val user = users.findById(userId)
            .orElseThrow { NoSuchElementException("User $userId not found") }

        val userCopies: List<BoardgameCopy> = user.boardgameCopies

        return userCopies.map { copy ->
            val latestLending = lendings.findAll()
                .filter { it.copy.copyId == copy.copyId }
                .maxByOrNull { it.loanTimestamp }

            val status = latestLending?.status?.statusCode ?: "AVAILABLE"

            UserBoardgameCopyDTO(
                boardgameTitle = copy.boardgame.title,
                status = status
            )
        }
    }
}
