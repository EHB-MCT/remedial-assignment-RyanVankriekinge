package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.BoardgameCopy
import be.ehb.gamelibrarian.repository.BoardgameCopyRepository
import be.ehb.gamelibrarian.repository.BoardgameRepository
import be.ehb.gamelibrarian.repository.BoardgameUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardgameCopyService(
    private val copies: BoardgameCopyRepository,
    private val games: BoardgameRepository,
    private val users: BoardgameUserRepository
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
}
