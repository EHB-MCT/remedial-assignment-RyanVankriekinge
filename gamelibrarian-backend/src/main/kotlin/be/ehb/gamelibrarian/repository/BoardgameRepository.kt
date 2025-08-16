package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.Boardgame
import org.springframework.data.jpa.repository.JpaRepository

interface BoardgameRepository : JpaRepository<Boardgame, Long>
