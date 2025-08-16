package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.Boardgame
import be.ehb.gamelibrarian.repository.BoardgameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardgameService(
    private val games: BoardgameRepository
) {
    @Transactional
    fun create(
        title: String,
        publisher: String,
        year: Int,
        minPlayers: Int,
        maxPlayers: Int,
        bggId: String? = null,
        bggURL: String? = null,
        imageURL: String? = null
    ): Boardgame {
        require(title.isNotBlank()) { "Title cannot be blank" }
        require(minPlayers > 0 && maxPlayers >= minPlayers) { "Invalid player counts" }
        return games.save(
            Boardgame(
                title = title,
                publisher = publisher,
                year = year,
                minPlayers = minPlayers,
                maxPlayers = maxPlayers,
                bggId = bggId,
                bggURL = bggURL,
                imageURL = imageURL
            )
        )
    }

    fun get(id: Long): Boardgame =
        games.findById(id).orElseThrow { NoSuchElementException("Boardgame $id not found") }
}
