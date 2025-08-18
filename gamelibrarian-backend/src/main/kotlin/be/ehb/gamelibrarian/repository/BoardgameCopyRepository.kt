package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.BoardgameCopy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardgameCopyRepository : JpaRepository<BoardgameCopy, Long> {
    fun findByOwnerBoardgameUserId(ownerId: Long): List<BoardgameCopy>
}
