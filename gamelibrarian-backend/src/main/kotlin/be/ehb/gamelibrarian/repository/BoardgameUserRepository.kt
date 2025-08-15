package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.BoardgameUser
import org.springframework.data.jpa.repository.JpaRepository

interface BoardgameUserRepository : JpaRepository<BoardgameUser, Long> {
    fun existsByEmail(email: String): Boolean
}
