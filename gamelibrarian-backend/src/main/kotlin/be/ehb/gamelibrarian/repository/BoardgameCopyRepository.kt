package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.BoardgameCopy
import org.springframework.data.jpa.repository.JpaRepository

interface BoardgameCopyRepository : JpaRepository<BoardgameCopy, Long>
